package com.wxmlabs.springca.server;

public class MediaType extends org.springframework.http.MediaType {

    /**
     * Public constant media type for {@code application/pkcs10}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS10;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS10}.
     */
    public static final String APPLICATION_PKCS10_VALUE = "application/pkcs10";

    /**
     * Public constant media type for {@code application/pkcs7-mime}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS7_MIME;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS7_MIME}.
     */
    public static final String APPLICATION_PKCS7_MIME_VALUE = "application/pkcs7-mime";

    /**
     * Public constant media type for {@code application/pkcs7-signature}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS7_SIGNATURE;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS7_SIGNATURE}.
     */
    public static final String APPLICATION_PKCS7_SIGNATURE_VALUE = "application/pkcs7-signature";

    /**
     * Public constant media type for {@code application/pkcs8}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS8;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS8}.
     */
    public static final String APPLICATION_PKCS8_VALUE = "application/pkcs8";

    /**
     * Public constant media type for {@code application/pkcs8-encrypted}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS8_ENCRYPTED;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS8_ENCRYPTED}.
     */
    public static final String APPLICATION_PKCS8_ENCRYPTED_VALUE = "application/pkcs8-encrypted";

    /**
     * Public constant media type for {@code application/pkcs12}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKCS12;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKCS12}.
     */
    public static final String APPLICATION_PKCS12_VALUE = "application/pkcs12";

    /**
     * Public constant media type for {@code application/pkix-attr-cert}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKIX_ATTR_CERT;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKIX_ATTR_CERT}.
     */
    public static final String APPLICATION_PKIX_ATTR_CERT_VALUE = "application/pkix-attr-cert";

    /**
     * Public constant media type for {@code application/pkix-cert}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKIX_CERT;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKIX_CERT}.
     */
    public static final String APPLICATION_PKIX_CERT_VALUE = "application/pkix-cert";

    /**
     * Public constant media type for {@code application/pkix-crl}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKIX_CRL;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKIX_CRL}.
     */
    public static final String APPLICATION_PKIX_CRL_VALUE = "application/pkix-crl";

    /**
     * Public constant media type for {@code application/pkix-pkipath}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKIX_PKIPATH;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKIX_PKIPATH}.
     */
    public static final String APPLICATION_PKIX_PKIPATH_VALUE = "application/pkix-pkipath";

    /**
     * Public constant media type for {@code application/pkixcmp}.
     */
    public static final org.springframework.http.MediaType APPLICATION_PKIXCMP;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_PKIXCMP}.
     */
    public static final String APPLICATION_PKIXCMP_VALUE = "application/pkixcmp";

    /**
     * Public constant media type for {@code application/cms}.
     */
    public static final org.springframework.http.MediaType APPLICATION_CMS;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_CMS}.
     */
    public static final String APPLICATION_CMS_VALUE = "application/cms";

    /**
     * Public constant media type for {@code application/cms;encapsulatingContent=signedData}.
     */
    public static final org.springframework.http.MediaType APPLICATION_CMS_SIGNED_DATA;

    /**
     * A String equivalent of {@link MediaType#APPLICATION_CMS_SIGNED_DATA}.
     */
    public static final String APPLICATION_CMS_SIGNED_DATA_VALUE = "application/cms;encapsulatingContent=signedData";

    static {
        APPLICATION_PKCS10 = valueOf(APPLICATION_PKCS10_VALUE);
        APPLICATION_PKCS7_MIME = valueOf(APPLICATION_PKCS7_MIME_VALUE);
        APPLICATION_PKCS7_SIGNATURE = valueOf(APPLICATION_PKCS7_SIGNATURE_VALUE);
        APPLICATION_PKCS8 = valueOf(APPLICATION_PKCS8_VALUE);
        APPLICATION_PKCS8_ENCRYPTED = valueOf(APPLICATION_PKCS8_ENCRYPTED_VALUE);
        APPLICATION_PKCS12 = valueOf(APPLICATION_PKCS12_VALUE);
        APPLICATION_PKIX_ATTR_CERT = valueOf(APPLICATION_PKIX_ATTR_CERT_VALUE);
        APPLICATION_PKIX_CERT = valueOf(APPLICATION_PKIX_CERT_VALUE);
        APPLICATION_PKIX_CRL = valueOf(APPLICATION_PKIX_CRL_VALUE);
        APPLICATION_PKIX_PKIPATH = valueOf(APPLICATION_PKIX_PKIPATH_VALUE);
        APPLICATION_PKIXCMP = valueOf(APPLICATION_PKIXCMP_VALUE);
        APPLICATION_CMS = valueOf(APPLICATION_CMS_VALUE);
        APPLICATION_CMS_SIGNED_DATA = valueOf(APPLICATION_CMS_SIGNED_DATA_VALUE);
    }

    private MediaType() {
        super("springca");
    }
}
