package com.wxmlabs.springca.core;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class KeyStoreManager {
    private Map<String, KeyStore> keyStoreMap = new HashMap<>();

    public KeyStore getKeyStore(String name) {
        return keyStoreMap.get(name);
    }

    public void register(String name, KeyStore keyStore) {
        keyStoreMap.put(name, keyStore);
    }
}
