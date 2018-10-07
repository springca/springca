package com.wxmlabs.springca.server.service;

import com.wxmlabs.springca.core.KeyStoreManager;
import com.wxmlabs.springca.server.conf.KeyStoreConf;
import com.wxmlabs.springca.server.conf.SpringCAConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

@Service
public class KeyStoreService {
    private static final Logger log = LoggerFactory.getLogger(KeyStoreService.class);

    private final KeyStoreManager manager;

    @Autowired
    public KeyStoreService(SpringCAConf springcaConf) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
        log.debug("KeyStoreService contractor...");
        KeyStoreConf conf = springcaConf.getKeystore();
        KeyStoreManager manager = new KeyStoreManager();
        KeyStore keyStore = KeyStore.getInstance(conf.getType());
        keyStore.load(new FileInputStream(conf.getParams().getFile()), conf.getParams().getPassword().toCharArray());
        manager.register("default", keyStore);
        this.manager = manager;
    }

    KeyStoreManager getKeyStoreManager() {
        return manager;
    }
}
