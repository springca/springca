package com.wxmlabs.springca.server.controller;

import com.wxmlabs.springca.server.service.CAService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/springca")
public class SpringCAController {
    private static final Logger log = LoggerFactory.getLogger(SpringCAController.class);

    @Autowired
    private CAService caService;

    @RequestMapping(value = "/enroll", method = POST)
    ResponseEntity<ByteArrayResource> enroll(@RequestParam String csr) throws CertificateEncodingException {
        String filename;
        byte[] data;

        X509Certificate x509 = caService.enrollCert(csr);
        filename = x509.getSerialNumber().toString(16) + ".cer";
        data = x509.getEncoded();

        return export(filename, data);
    }

    @RequestMapping(value = "/issue", method = POST)
    ResponseEntity<ByteArrayResource> issue(@RequestParam String commonName) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, OperatorCreationException, InvalidAlgorithmParameterException, PKCSException, NoSuchProviderException {
        log.debug("issue for " + commonName);
        String filename;
        byte[] data;

        KeyStore pkcs12 = caService.issueCert(commonName);
        String alias = pkcs12.aliases().nextElement();
        X509Certificate x509 = (X509Certificate) pkcs12.getCertificate(alias);
        filename = x509.getSerialNumber().toString(16) + ".p12";

        ByteArrayOutputStream p12Out = new ByteArrayOutputStream();
        pkcs12.store(p12Out, "springca.wxmlabs.com".toCharArray());
        data = p12Out.toByteArray();

        return export(filename, data);
    }

    private ResponseEntity<ByteArrayResource> export(String filename, byte[] data) {
        if (data == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        // RFC 6266 - Use of the Content-Disposition Header Field in the Hypertext Transfer Protocol (HTTP)
        // @link https://tools.ietf.org/html/rfc6266
        headers.setContentDisposition(
            ContentDisposition
                .builder("attachment")
                .filename(filename, StandardCharsets.UTF_8)
                .build()
        );

        try {
            String etag;
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] digest = md.digest(data);
            etag = Base64.encodeBase64URLSafeString(digest);
            headers.setETag("\"" + etag + "\"");
        } catch (NoSuchAlgorithmException ignore) {
        }

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentLength(data.length)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(new ByteArrayResource(data));
    }
}
