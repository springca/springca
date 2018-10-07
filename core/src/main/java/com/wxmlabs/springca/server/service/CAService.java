package com.wxmlabs.springca.server.service;

import com.wxmlabs.springca.core.SpringCA;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Service
public class CAService {
    private static final Logger log = LoggerFactory.getLogger(CAService.class);

    private SpringCA springCA;

    @Autowired
    public CAService(IssuerService issuerService) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        log.debug("CAService contractor...");
        this.springCA = new SpringCA(issuerService.getIssuerManager());
    }


    public X509Certificate enrollCert(String csr) {
        return springCA.enrollCert(csr, false);
    }

    public KeyStore issueCert(String commonName) throws OperatorCreationException, PKCSException, NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        return springCA.issueCert(commonName);
    }
}
