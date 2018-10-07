package com.wxmlabs.springca.core;

import com.wxmlabs.springca.util.CertUtil;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SpringCA {
    private static final Logger log = LoggerFactory.getLogger(SpringCA.class);

    private final ConcurrentHashMap<String, CA> caMap = new ConcurrentHashMap<String, CA>(2);
    private final char[] EMPTY = new char[0];

    public SpringCA(IssuerManager issuerManager) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        Issuer issuer = issuerManager.getIssuer("default");
        KeyStore keyStore = issuer.getKeyStore();
        String issuerKeyAlias = issuer.getIssuerKeyAlias();
        PrivateKey issuerKey = (PrivateKey) keyStore.getKey(issuerKeyAlias, EMPTY);
        Certificate[] chain = keyStore.getCertificateChain(issuerKeyAlias);

        String keyAlg = CertUtil.findAlgName(issuerKey.getAlgorithm());
        caMap.put(keyAlg, new SpringCA.CA(keyAlg + " SpringCA", issuerKey, chain));
    }

    /**
     * @param csr Base64 encoded PKCS10
     * @return Base64 encoded X.509 Certificate
     */
    public X509Certificate enrollCert(String csr, boolean skipVerify) {
        try {
            return _issueCert(CertUtil.parseCsr(csr), skipVerify).getEntityCert();
        } catch (CertificateException | IOException | PKCSException | OperatorCreationException e) {
            throw new SpringCAException(e.getMessage(), e);
        }
    }

    private Response _issueCert(PKCS10CertificationRequest certificationRequest, boolean ignoreCsrSignature) throws CertificateException, OperatorCreationException, PKCSException {
        SubjectPublicKeyInfo subjectPublicKeyInfo = certificationRequest.getSubjectPublicKeyInfo();
        if (ignoreCsrSignature || certificationRequest.isSignatureValid(new JcaContentVerifierProviderBuilder().build(subjectPublicKeyInfo))) {
            CA ca = findCA(subjectPublicKeyInfo.getAlgorithm());
            Certificate[] chain = ca.getChain();
            X509Certificate caCert = (X509Certificate) chain[0];
            PrivateKey issuerKey = ca.getIssuerKey();
            X509Certificate certificate = CertUtil.issueCert(certificationRequest, issuerKey, caCert, false);
            return new Response(certificate, chain);
        } else {
            throw new CertificateException("Invalid CertificateRequest.");
        }
    }

    private CA findCA(AlgorithmIdentifier keyAlg) {
        String algName = CertUtil.findAlgName(keyAlg);
        CA ca = caMap.get(algName);
        if (ca == null) {
            ca = caMap.values().iterator().next();
        }
        if (ca == null) {
            throw new SpringCAException("No CA working");
        }
        return ca;
    }

    public KeyStore issueCert(String commonName) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException, OperatorCreationException, CertificateException, PKCSException, KeyStoreException {
        String keyAlg = "RSA";
        KeyPair keyPair = CertUtil.genKeyPair(keyAlg);
        PKCS10CertificationRequest csr = CertUtil.genCsr("O=wxmLabs(测试), OU=" + keyAlg + "(测试), CN=" + commonName, keyPair);
        Response issueResponse = _issueCert(csr, false);

        KeyStore pkcs12 = KeyStore.getInstance("PKCS12");
        pkcs12.load(null);
        pkcs12.setKeyEntry("SpringCA_" + commonName, keyPair.getPrivate(), "springca.wxmlabs.com".toCharArray(), issueResponse.getChain());
        return pkcs12;
    }


    class CA {
        private final String name;
        private final PrivateKey issuerKey;
        private final Certificate[] chain;

        CA(String name, PrivateKey issuerKey, Certificate[] chain) {
            this.name = name;
            this.issuerKey = issuerKey;
            this.chain = chain;
        }

        public String getName() {
            return name;
        }

        public PrivateKey getIssuerKey() {
            return issuerKey;
        }

        public Certificate[] getChain() {
            return chain;
        }
    }

    class Response {
        private X509Certificate entityCert;
        private Certificate[] chain;

        public Response(X509Certificate entityCert, Certificate[] chain) {
            this.entityCert = entityCert;
            this.chain = chain;
        }

        public X509Certificate getEntityCert() {
            return entityCert;
        }

        public byte[] buildPkcs7Certchain() throws CertificateException, NoSuchProviderException {
            List<Certificate> certChain = Arrays.asList(getChain());
            return CertUtil.buildCertPath(certChain).getEncoded("PKCS7");
        }

        public Certificate[] getChain() {
            return new Certificate[]{entityCert, chain[0], chain[1]};
        }
    }

}
