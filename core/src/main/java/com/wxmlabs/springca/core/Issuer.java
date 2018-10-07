package com.wxmlabs.springca.core;

import java.security.KeyStore;

public class Issuer {
    private final KeyStore keyStore;
    private final String issuerKeyAlias;

    public Issuer(KeyStore keyStore, String issuerKeyAlias) {
        this.keyStore = keyStore;
        this.issuerKeyAlias = issuerKeyAlias;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getIssuerKeyAlias() {
        return issuerKeyAlias;
    }
}
