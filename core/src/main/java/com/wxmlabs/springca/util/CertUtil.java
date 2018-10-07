package com.wxmlabs.springca.util;

import com.wxmlabs.springca.core.SpringCAException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CertUtil {
    private static final String PROVIDER_NAME = "BC";
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";
    private static final String SUFFIX = "-----";
    private static final String CERT_REQ = "CERTIFICATE REQUEST";
    private static final HashMap<String, String> signAlgMap = new HashMap<String, String>(2);
    private static final HashMap<String, String> algNameMap = new HashMap<String, String>(2);

    static {
        signAlgMap.put("RSA", "SHA256withRSA");
        signAlgMap.put("EC", "SM3withSM2");
    }

    static {
        algNameMap.put("1.2.840.113549.1.1.1", "RSA");
        algNameMap.put("1.2.840.10045.2.1", "EC");
    }

    public static String findAlgName(String keyAlg) {
        if (keyAlg.contains(".")) {
            return algNameMap.get(keyAlg);
        }
        return keyAlg;
    }

    public static String findAlgName(AlgorithmIdentifier algorithmIdentifier) {
        return findAlgName(algorithmIdentifier.getAlgorithm().getId());
    }

    private static Object convertToJcaObject(Object obj) {
        if (obj instanceof X509CertificateHolder) {
            JcaX509CertificateConverter converter = new JcaX509CertificateConverter().setProvider(PROVIDER_NAME);
            try {
                return converter.getCertificate((X509CertificateHolder) obj);
            } catch (CertificateException e) {
                throw new SpringCAException(e.getMessage(), e);
            }
        } else if (obj instanceof X509CRLHolder) {
            JcaX509CRLConverter converter = new JcaX509CRLConverter().setProvider(PROVIDER_NAME);
            try {
                return converter.getCRL((X509CRLHolder) obj);
            } catch (CRLException e) {
                throw new SpringCAException(e.getMessage(), e);
            }
        }
        return null;
    }

    private static String defaultSigAlg(PrivateKey privateKey) {
        String keyAlg = privateKey.getAlgorithm();
        String algName = findAlgName(keyAlg);
        String sigAlg = signAlgMap.get(algName);
        if (sigAlg == null)
            throw new SpringCAException("can not find default signature algorithm for " + keyAlg);
        return sigAlg;
    }

    public static PKCS10CertificationRequest parseCsr(String csr) throws IOException {
        if (!csr.contains(BEGIN + CERT_REQ)) {
            StringWriter stringWriter = new StringWriter();
            stringWriter.write(BEGIN + CERT_REQ + SUFFIX);
            stringWriter.write("\n");
            stringWriter.write(csr);
            stringWriter.write("\n");
            stringWriter.write(END + CERT_REQ + SUFFIX);
            csr = stringWriter.toString();
        }
        PEMParser pemParser = new PEMParser(new StringReader(csr));
        return (PKCS10CertificationRequest) pemParser.readObject();
    }

    public static X509Certificate issueCert(PKCS10CertificationRequest certificationRequest, PrivateKey issuerKey, X509Certificate issuerCert, boolean isCACert) {
        try {
            X500Name subjectX500Name = certificationRequest.getSubject();
            SubjectPublicKeyInfo subjectPublicKeyInfo = certificationRequest.getSubjectPublicKeyInfo();
            BigInteger serialnumber = randomNumber(16);
            X500Name issuerX500Name;
            if (issuerCert == null) {
                issuerX500Name = subjectX500Name;
            } else {
                issuerX500Name = X500Name.getInstance(issuerCert.getSubjectX500Principal().getEncoded());
            }
            Date notBefore = new Date();
            Date notAfter = new Date(notBefore.getTime() + 86400000);

            JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
            X509v3CertificateBuilder builder = new X509v3CertificateBuilder(issuerX500Name, serialnumber, notBefore, notAfter, subjectX500Name, subjectPublicKeyInfo);
            builder.addExtension(
                Extension.subjectKeyIdentifier,
                false,
                extUtils.createSubjectKeyIdentifier(subjectPublicKeyInfo));

            if (issuerCert == null) {
                builder.addExtension(
                    Extension.authorityKeyIdentifier,
                    false,
                    extUtils.createAuthorityKeyIdentifier(subjectPublicKeyInfo));
            } else {
                builder.addExtension(
                    Extension.authorityKeyIdentifier,
                    false,
                    extUtils.createAuthorityKeyIdentifier(issuerCert));
            }

            builder.addExtension(
                Extension.basicConstraints,
                false,
                new BasicConstraints(isCACert));
            ContentSigner signer = new JcaContentSignerBuilder(defaultSigAlg(issuerKey)).setProvider(PROVIDER_NAME).build(issuerKey);
            return (X509Certificate) convertToJcaObject(builder.build(signer));
        } catch (OperatorCreationException e) {
            throw new SpringCAException(e.getMessage(), e);
        } catch (CertIOException e) {
            throw new SpringCAException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new SpringCAException(e.getMessage(), e);
        } catch (CertificateException e) {
            throw new SpringCAException(e.getMessage(), e);
        }
    }

    public static CertPath buildCertPath(List<Certificate> certChain) throws CertificateException, NoSuchProviderException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509", PROVIDER_NAME);
        return factory.generateCertPath(certChain);
    }

    public static PKCS10CertificationRequest genCsr(String subjectDN, KeyPair keyPair) throws IOException, OperatorCreationException {
        X500Name x500SubjectDN = new X500Name(subjectDN);
        String sigAlg = defaultSigAlg(keyPair.getPrivate());
        SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());
        PKCS10CertificationRequestBuilder builder = new PKCS10CertificationRequestBuilder(x500SubjectDN, publicKeyInfo);
        // 证书主体附加信息属性值 其ID为固定的 OBJECT IDENTIFIER (1.2.840.113549.1.9.14) extensionRequest 值为空的Sequence
        builder.addAttribute(new ASN1ObjectIdentifier("1.2.840.113549.1.9.14"), new DERSequence());
        ContentSigner signer = new JcaContentSignerBuilder(sigAlg).setProvider(PROVIDER_NAME).build(keyPair.getPrivate());
        return new PKCS10CertificationRequest(builder.build(signer).getEncoded());
    }

    public static KeyPair genKeyPair(String keyAlg) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        if ("SM2".equals(keyAlg) || "EC".equals(keyAlg)) {
            return genSM2KeyPair();
        } else {
            return genRSAKeyPair();
        }
    }

    private static KeyPair genRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", PROVIDER_NAME);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.genKeyPair();
    }

    private static KeyPair genSM2KeyPair() throws InvalidAlgorithmParameterException, NoSuchProviderException, NoSuchAlgorithmException {
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("EC", PROVIDER_NAME);
        kpGen.initialize(new ECNamedCurveGenParameterSpec("sm2p256v1"));
        return kpGen.genKeyPair();
    }

    private static final SecureRandom numberGenerator = new SecureRandom();

    private static BigInteger randomNumber(@SuppressWarnings("SameParameterValue") int length) {
        byte[] randomBytes = new byte[length];
        numberGenerator.nextBytes(randomBytes);
        return new BigInteger(1, randomBytes);
    }

}
