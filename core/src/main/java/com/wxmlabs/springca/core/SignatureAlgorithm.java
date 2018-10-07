package com.wxmlabs.springca.core;

import com.wxmlabs.springca.util.ECParameterSpecUtil;

import java.security.Key;
import java.security.interfaces.DSAKey;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.util.HashMap;
import java.util.Map;

public class SignatureAlgorithm {

    private static final Map<String, String> encryptionAlgs = new HashMap<String, String>();

    static {
        // ANSI X9.57 algorithm
        encryptionAlgs.put("1.2.840.10040.4.1", "DSA"); // esa
        // PKCS #1
        encryptionAlgs.put("1.2.840.113549.1.1.1", "RSA"); // rsaEncryption
        // ANSI X9.62 public key type
        encryptionAlgs.put("1.2.840.10045.2.1", "EC"); // ecPublicKey
        // China GM Standards Committee
        encryptionAlgs.put("1.2.156.10197.1.301 ", "SM2"); // sm2ECC
    }

    private HashMap<String, String> defaultAlgMap = new HashMap<>(3);

    private void fillDefaultAlgMap() {
        defaultAlgMap.put("RSA", "SHA256withRSA");
        defaultAlgMap.put("ECDSA", "SHA256withECDSA");
        defaultAlgMap.put("SM2", "SM3withSM2");
    }

    public SignatureAlgorithm() {
        fillDefaultAlgMap();
    }



    private String getEncryptionAlgName(Key asymmetricKey) {
        if (asymmetricKey instanceof RSAKey) {
            return "RSA";
        } else if (asymmetricKey instanceof DSAKey) {
            return "DSA";
        } else if (asymmetricKey instanceof ECKey) {
            ECParameterSpec spec = ((ECKey) asymmetricKey).getParams();
            if (ECParameterSpecUtil.isSM2ECC(spec)) {
                return "SM2";
            } else {
                return "ECDSA";
            }
        } else {
            String keyAlg = asymmetricKey.getAlgorithm();
            String encryptionAlg = encryptionAlgs.get(keyAlg);
            if (encryptionAlg == null) encryptionAlg = keyAlg;
            return encryptionAlg;
        }
    }
}
