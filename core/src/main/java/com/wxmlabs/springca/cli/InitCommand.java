package com.wxmlabs.springca.cli;

import com.wxmlabs.springca.core.SpringCAException;
import com.wxmlabs.springca.util.CertUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class InitCommand {

    void execute() {
        InitCommand command = new InitCommand();
        try {
            command.init();
        } catch (Exception e) {
            throw new RuntimeException("init ca service failed.", e);
        }
    }

    private KeyStore keyStore;
    private final char[] password = "springca.wxmlabs.com".toCharArray();
    private final char[] EMPTY = new char[0];

    private void init() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        Security.addProvider(new BouncyCastleProvider());
        if (keyStore == null) {
            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(null);
        }
        initCA(keyStore, "RSA");
//        initCA(keyStore, "SM2");
        keyStore.store(new FileOutputStream(getDefaultJksFile()), password);
    }

    private File getDefaultJksFile() {
        File defaultJksFile = new File(System.getProperty("user.home"), ".springca/issuer/default.jks");
        if (!defaultJksFile.getParentFile().exists()) {
            defaultJksFile.getParentFile().mkdirs();
        }
        return defaultJksFile;
    }

    private void initCA(KeyStore keyStore, String keyAlg) {
        try {
            // self sign Root CA Certificate
            KeyPair rootKeyPair = CertUtil.genKeyPair(keyAlg);
            PKCS10CertificationRequest rootCaCsr = CertUtil.genCsr("O=wxmLabs(测试), OU=" + keyAlg + "(测试), CN=Root(测试)", rootKeyPair);
            X509Certificate rootCaCert = CertUtil.issueCert(rootCaCsr, rootKeyPair.getPrivate(), null, true);
            keyStore.setKeyEntry(keyAlg + "_Root", rootKeyPair.getPrivate(), EMPTY, new Certificate[]{rootCaCert});

            // Root CA sign Issuer CA Certificate
            KeyPair issuerKeyPair = CertUtil.genKeyPair(keyAlg);
            PKCS10CertificationRequest issuerCaCsr = CertUtil.genCsr("O=wxmLabs(测试), OU=" + keyAlg + "(测试), CN=SpringCA(测试)", issuerKeyPair);
            X509Certificate issuerCaCert = CertUtil.issueCert(issuerCaCsr, rootKeyPair.getPrivate(), rootCaCert, true);
            keyStore.setKeyEntry(keyAlg + "_Issuer", rootKeyPair.getPrivate(), EMPTY, new Certificate[]{issuerCaCert, rootCaCert});
        } catch (NoSuchAlgorithmException | KeyStoreException | IOException | OperatorCreationException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new SpringCAException(e.getMessage(), e);
        }
    }
}
