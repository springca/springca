package com.wxmlabs.springca.server.service;

import com.wxmlabs.springca.core.Issuer;
import com.wxmlabs.springca.core.IssuerManager;
import com.wxmlabs.springca.core.KeyStoreManager;
import com.wxmlabs.springca.server.conf.IssuerConf;
import com.wxmlabs.springca.server.conf.SpringCAConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuerService {
    private static final Logger log = LoggerFactory.getLogger(IssuerService.class);

    private final IssuerManager manager;

    @Autowired
    public IssuerService(SpringCAConf springcaConf, KeyStoreService keyStoreService) {
        log.debug("IssuerService contractor...");
        KeyStoreManager keyStoreManager = keyStoreService.getKeyStoreManager();
        IssuerConf conf = springcaConf.getIssuer();
        IssuerManager manager = new IssuerManager();
        manager.register("default", new Issuer(keyStoreManager.getKeyStore("default"), conf.getKey().getAlias()));
        this.manager = manager;
    }

    public IssuerManager getIssuerManager() {
        return manager;
    }
}
