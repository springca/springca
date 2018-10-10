package com.wxmlabs.springca.server.controller;

import com.wxmlabs.springca.server.service.CAService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
import java.util.Collections;
import java.util.Map;

import static com.wxmlabs.springca.server.MediaType.APPLICATION_OCTET_STREAM;
import static com.wxmlabs.springca.server.MediaType.APPLICATION_PDF;
import static com.wxmlabs.springca.server.MediaType.APPLICATION_PKCS10;
import static com.wxmlabs.springca.server.MediaType.APPLICATION_PKCS12;
import static com.wxmlabs.springca.server.MediaType.APPLICATION_PKIX_CERT;
import static com.wxmlabs.springca.server.MediaType.IMAGE_PNG;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/springca")
public class SpringCAController {
    private static final Logger log = LoggerFactory.getLogger(SpringCAController.class);

    @Autowired
    private CAService caService;

    @RequestMapping(value = "/enroll", method = POST)
    ResponseEntity enroll(HttpServletRequest request) {
        String filename;
        byte[] data;

        try {
            String csr;
            if (MediaType.valueOf(request.getContentType()).includes(APPLICATION_PKCS10)) {
                String encoding = request.getHeader("Content-Transfer-Encoding");
                if (encoding == null || encoding.isEmpty()) { // binary encoding
                    csr = Base64.encodeBase64String(IOUtils.toByteArray(request.getInputStream()));
                } else { // must be base64 encoding
                    csr = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
                }
            } else if (MediaType.valueOf(request.getContentType()).includes(APPLICATION_JSON)) {
                csr = (String) new JacksonJsonParser().parseMap(IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8)).get("csr");
            } else {
                csr = request.getParameter("csr");
            }
            log.debug("Certificate request: {}", csr);
            if (csr == null || csr.isEmpty()) {
                return ResponseEntity.badRequest().body("Required String parameter 'csr' is not present");
            }


            X509Certificate x509 = caService.enrollCert(csr);
            filename = x509.getSerialNumber().toString(16) + ".cer";
            data = x509.getEncoded();
            return export(filename, data);
        } catch (CertificateEncodingException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/issue", method = POST)
    ResponseEntity issue(@RequestParam String commonName) {
        log.debug("issue for " + commonName);
        if (commonName == null)
            return ResponseEntity.badRequest().body("commonName can not be null");

        String filename;
        byte[] data;

        try {
            KeyStore pkcs12 = caService.issueCert(commonName);
            String alias = pkcs12.aliases().nextElement();
            X509Certificate x509 = (X509Certificate) pkcs12.getCertificate(alias);
            filename = x509.getSerialNumber().toString(16) + ".pfx";

            ByteArrayOutputStream p12Out = new ByteArrayOutputStream();
            pkcs12.store(p12Out, "springca.wxmlabs.com".toCharArray());
            data = p12Out.toByteArray();
            return export(filename, data);
        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | OperatorCreationException | PKCSException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private ResponseEntity<ByteArrayResource> export(String filename, byte[] data) {
        if (data == null) {
            return null;
        }

        String suffix = filename.substring(filename.lastIndexOf('.'));
        MediaType contentType;
        Map<String, String> parameters = Collections.singletonMap("name", filename);
        switch (suffix) {
            case ".p10":
            case ".csr": // 非标准后缀
                contentType = new MediaType(APPLICATION_PKCS10, parameters);
                break;
            case ".p12":
            case ".pfx":
                contentType = new MediaType(APPLICATION_PKCS12, parameters);
                break;
            case ".cer":
            case ".crt": // 非标准后缀
                contentType = new MediaType(APPLICATION_PKIX_CERT, parameters);
                break;
            case ".pdf":
                contentType = new MediaType(APPLICATION_PDF, parameters);
                break;
            case ".png":
                contentType = new MediaType(IMAGE_PNG, parameters);
                break;
            default:
                contentType = APPLICATION_OCTET_STREAM;
                break;
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
            .contentType(contentType)
            .body(new ByteArrayResource(data));
    }
}
