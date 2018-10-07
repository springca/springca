package com.wxmlabs.springca.server.conf;

public class IssuerConf {
    private IssuerKey key;

    public IssuerKey getKey() {
        return key;
    }

    public void setKey(IssuerKey key) {
        this.key = key;
    }

    public static class IssuerKey {
        private String keystore;
        private String alias;

        public String getKeystore() {
            return keystore;
        }

        public void setKeystore(String keystore) {
            this.keystore = keystore;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
