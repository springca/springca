package com.wxmlabs.springca.server.conf;

public class SpringCAConf {
    private KeyStoreConf keystore;
    private IssuerConf issuer;

    public KeyStoreConf getKeystore() {
        return keystore;
    }

    public void setKeystore(KeyStoreConf keystore) {
        this.keystore = keystore;
    }

    public IssuerConf getIssuer() {
        return issuer;
    }

    public void setIssuer(IssuerConf issuer) {
        this.issuer = issuer;
    }
}
