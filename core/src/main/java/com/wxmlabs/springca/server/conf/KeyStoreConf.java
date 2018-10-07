package com.wxmlabs.springca.server.conf;

import java.io.File;


public class KeyStoreConf {
    private String type;
    private Parameters params;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Parameters getParams() {
        return params;
    }

    public void setParams(Parameters params) {
        this.params = params;
    }

    public static class Parameters {
        private File file;
        private String password;
        private String passwordEncryptionCodec;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordEncryptionCodec() {
            return passwordEncryptionCodec;
        }

        public void setPasswordEncryptionCodec(String passwordEncryptionCodec) {
            this.passwordEncryptionCodec = passwordEncryptionCodec;
        }
    }
}
