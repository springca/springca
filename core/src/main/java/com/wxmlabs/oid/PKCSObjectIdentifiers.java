/**
 * Copyright (c) 2000-2018 The Legion of the Bouncy Castle Inc. (http://www.bouncycastle.org)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.wxmlabs.oid;

/**
 * pkcs-1 OBJECT IDENTIFIER ::=<p>
 * { iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 1 }
 */
public interface PKCSObjectIdentifiers {
    /**
     * PKCS#1: 1.2.840.113549.1.1
     */
    ObjectIdentifier pkcs_1 = new ObjectIdentifier("1.2.840.113549.1.1");
    /**
     * PKCS#1: 1.2.840.113549.1.1.1
     */
    ObjectIdentifier rsaEncryption = pkcs_1.branch("1");
    /**
     * PKCS#1: 1.2.840.113549.1.1.2
     */
    ObjectIdentifier md2WithRSAEncryption = pkcs_1.branch("2");
    /**
     * PKCS#1: 1.2.840.113549.1.1.3
     */
    ObjectIdentifier md4WithRSAEncryption = pkcs_1.branch("3");
    /**
     * PKCS#1: 1.2.840.113549.1.1.4
     */
    ObjectIdentifier md5WithRSAEncryption = pkcs_1.branch("4");
    /**
     * PKCS#1: 1.2.840.113549.1.1.5
     */
    ObjectIdentifier sha1WithRSAEncryption = pkcs_1.branch("5");
    /**
     * PKCS#1: 1.2.840.113549.1.1.6
     */
    ObjectIdentifier srsaOAEPEncryptionSET = pkcs_1.branch("6");
    /**
     * PKCS#1: 1.2.840.113549.1.1.7
     */
    ObjectIdentifier id_RSAES_OAEP = pkcs_1.branch("7");
    /**
     * PKCS#1: 1.2.840.113549.1.1.8
     */
    ObjectIdentifier id_mgf1 = pkcs_1.branch("8");
    /**
     * PKCS#1: 1.2.840.113549.1.1.9
     */
    ObjectIdentifier id_pSpecified = pkcs_1.branch("9");
    /**
     * PKCS#1: 1.2.840.113549.1.1.10
     */
    ObjectIdentifier id_RSASSA_PSS = pkcs_1.branch("10");
    /**
     * PKCS#1: 1.2.840.113549.1.1.11
     */
    ObjectIdentifier sha256WithRSAEncryption = pkcs_1.branch("11");
    /**
     * PKCS#1: 1.2.840.113549.1.1.12
     */
    ObjectIdentifier sha384WithRSAEncryption = pkcs_1.branch("12");
    /**
     * PKCS#1: 1.2.840.113549.1.1.13
     */
    ObjectIdentifier sha512WithRSAEncryption = pkcs_1.branch("13");
    /**
     * PKCS#1: 1.2.840.113549.1.1.14
     */
    ObjectIdentifier sha224WithRSAEncryption = pkcs_1.branch("14");
    /**
     * PKCS#1: 1.2.840.113549.1.1.15
     */
    ObjectIdentifier sha512_224WithRSAEncryption = pkcs_1.branch("15");
    /**
     * PKCS#1: 1.2.840.113549.1.1.16
     */
    ObjectIdentifier sha512_256WithRSAEncryption = pkcs_1.branch("16");

    //
    // pkcs-3 OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 3 }
    //
    /**
     * PKCS#3: 1.2.840.113549.1.3
     */
    ObjectIdentifier pkcs_3 = new ObjectIdentifier("1.2.840.113549.1.3");
    /**
     * PKCS#3: 1.2.840.113549.1.3.1
     */
    ObjectIdentifier dhKeyAgreement = pkcs_3.branch("1");

    //
    // pkcs-5 OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 5 }
    //
    /**
     * PKCS#5: 1.2.840.113549.1.5
     */
    ObjectIdentifier pkcs_5 = new ObjectIdentifier("1.2.840.113549.1.5");

    /**
     * PKCS#5: 1.2.840.113549.1.5.1
     */
    ObjectIdentifier pbeWithMD2AndDES_CBC = pkcs_5.branch("1");
    /**
     * PKCS#5: 1.2.840.113549.1.5.4
     */
    ObjectIdentifier pbeWithMD2AndRC2_CBC = pkcs_5.branch("4");
    /**
     * PKCS#5: 1.2.840.113549.1.5.3
     */
    ObjectIdentifier pbeWithMD5AndDES_CBC = pkcs_5.branch("3");
    /**
     * PKCS#5: 1.2.840.113549.1.5.6
     */
    ObjectIdentifier pbeWithMD5AndRC2_CBC = pkcs_5.branch("6");
    /**
     * PKCS#5: 1.2.840.113549.1.5.10
     */
    ObjectIdentifier pbeWithSHA1AndDES_CBC = pkcs_5.branch("10");
    /**
     * PKCS#5: 1.2.840.113549.1.5.11
     */
    ObjectIdentifier pbeWithSHA1AndRC2_CBC = pkcs_5.branch("11");
    /**
     * PKCS#5: 1.2.840.113549.1.5.13
     */
    ObjectIdentifier id_PBES2 = pkcs_5.branch("13");
    /**
     * PKCS#5: 1.2.840.113549.1.5.12
     */
    ObjectIdentifier id_PBKDF2 = pkcs_5.branch("12");

    //
    // encryptionAlgorithm OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) 3 }
    //
    /**
     * 1.2.840.113549.3
     */
    ObjectIdentifier encryptionAlgorithm = new ObjectIdentifier("1.2.840.113549.3");

    /**
     * 1.2.840.113549.3.7
     */
    ObjectIdentifier des_EDE3_CBC = encryptionAlgorithm.branch("7");
    /**
     * 1.2.840.113549.3.2
     */
    ObjectIdentifier RC2_CBC = encryptionAlgorithm.branch("2");
    /**
     * 1.2.840.113549.3.4
     */
    ObjectIdentifier rc4 = encryptionAlgorithm.branch("4");

    //
    // object identifiers for digests
    //
    /**
     * 1.2.840.113549.2
     */
    ObjectIdentifier digestAlgorithm = new ObjectIdentifier("1.2.840.113549.2");
    //
    // md2 OBJECT IDENTIFIER ::=
    //      {iso(1) member-body(2) US(840) rsadsi(113549) digestAlgorithm(2) 2}
    //
    /**
     * 1.2.840.113549.2.2
     */
    ObjectIdentifier md2 = digestAlgorithm.branch("2");

    //
    // md4 OBJECT IDENTIFIER ::=
    //      {iso(1) member-body(2) US(840) rsadsi(113549) digestAlgorithm(2) 4}
    //
    /**
     * 1.2.840.113549.2.4
     */
    ObjectIdentifier md4 = digestAlgorithm.branch("4");

    //
    // md5 OBJECT IDENTIFIER ::=
    //      {iso(1) member-body(2) US(840) rsadsi(113549) digestAlgorithm(2) 5}
    //
    /**
     * 1.2.840.113549.2.5
     */
    ObjectIdentifier md5 = digestAlgorithm.branch("5");

    /**
     * 1.2.840.113549.2.7
     */
    ObjectIdentifier id_hmacWithSHA1 = digestAlgorithm.branch("7").intern();
    /**
     * 1.2.840.113549.2.8
     */
    ObjectIdentifier id_hmacWithSHA224 = digestAlgorithm.branch("8").intern();
    /**
     * 1.2.840.113549.2.9
     */
    ObjectIdentifier id_hmacWithSHA256 = digestAlgorithm.branch("9").intern();
    /**
     * 1.2.840.113549.2.10
     */
    ObjectIdentifier id_hmacWithSHA384 = digestAlgorithm.branch("10").intern();
    /**
     * 1.2.840.113549.2.11
     */
    ObjectIdentifier id_hmacWithSHA512 = digestAlgorithm.branch("11").intern();

    //
    // pkcs-7 OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 7 }
    //
    /**
     * pkcs#7: 1.2.840.113549.1.7
     */
    ObjectIdentifier pkcs_7 = new ObjectIdentifier("1.2.840.113549.1.7").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.1
     */
    ObjectIdentifier data = new ObjectIdentifier("1.2.840.113549.1.7.1").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.2
     */
    ObjectIdentifier signedData = new ObjectIdentifier("1.2.840.113549.1.7.2").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.3
     */
    ObjectIdentifier envelopedData = new ObjectIdentifier("1.2.840.113549.1.7.3").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.4
     */
    ObjectIdentifier signedAndEnvelopedData = new ObjectIdentifier("1.2.840.113549.1.7.4").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.5
     */
    ObjectIdentifier digestedData = new ObjectIdentifier("1.2.840.113549.1.7.5").intern();
    /**
     * PKCS#7: 1.2.840.113549.1.7.76
     */
    ObjectIdentifier encryptedData = new ObjectIdentifier("1.2.840.113549.1.7.6").intern();

    //
    // pkcs-9 OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 9 }
    //
    /**
     * PKCS#9: 1.2.840.113549.1.9
     */
    ObjectIdentifier pkcs_9 = new ObjectIdentifier("1.2.840.113549.1.9");

    /**
     * PKCS#9: 1.2.840.113549.1.9.1
     */
    ObjectIdentifier pkcs_9_at_emailAddress = pkcs_9.branch("1").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.2
     */
    ObjectIdentifier pkcs_9_at_unstructuredName = pkcs_9.branch("2").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.3
     */
    ObjectIdentifier pkcs_9_at_contentType = pkcs_9.branch("3").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.4
     */
    ObjectIdentifier pkcs_9_at_messageDigest = pkcs_9.branch("4").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.5
     */
    ObjectIdentifier pkcs_9_at_signingTime = pkcs_9.branch("5").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.6
     */
    ObjectIdentifier pkcs_9_at_counterSignature = pkcs_9.branch("6").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.7
     */
    ObjectIdentifier pkcs_9_at_challengePassword = pkcs_9.branch("7").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.8
     */
    ObjectIdentifier pkcs_9_at_unstructuredAddress = pkcs_9.branch("8").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.9
     */
    ObjectIdentifier pkcs_9_at_extendedCertificateAttributes = pkcs_9.branch("9").intern();

    /**
     * PKCS#9: 1.2.840.113549.1.9.13
     */
    ObjectIdentifier pkcs_9_at_signingDescription = pkcs_9.branch("13").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.14
     */
    ObjectIdentifier pkcs_9_at_extensionRequest = pkcs_9.branch("14").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.15
     */
    ObjectIdentifier pkcs_9_at_smimeCapabilities = pkcs_9.branch("15").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.16
     */
    ObjectIdentifier id_smime = pkcs_9.branch("16").intern();

    /**
     * PKCS#9: 1.2.840.113549.1.9.20
     */
    ObjectIdentifier pkcs_9_at_friendlyName = pkcs_9.branch("20").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.21
     */
    ObjectIdentifier pkcs_9_at_localKeyId = pkcs_9.branch("21").intern();

    /**
     * PKCS#9: 1.2.840.113549.1.9.22.1
     *
     * @deprecated use x509Certificate instead
     */
    ObjectIdentifier x509certType = pkcs_9.branch("22.1");

    /**
     * PKCS#9: 1.2.840.113549.1.9.22
     */
    ObjectIdentifier certTypes = pkcs_9.branch("22");
    /**
     * PKCS#9: 1.2.840.113549.1.9.22.1
     */
    ObjectIdentifier x509Certificate = certTypes.branch("1").intern();
    /**
     * PKCS#9: 1.2.840.113549.1.9.22.2
     */
    ObjectIdentifier sdsiCertificate = certTypes.branch("2").intern();

    /**
     * PKCS#9: 1.2.840.113549.1.9.23
     */
    ObjectIdentifier crlTypes = pkcs_9.branch("23");
    /**
     * PKCS#9: 1.2.840.113549.1.9.23.1
     */
    ObjectIdentifier x509Crl = crlTypes.branch("1").intern();

    /**
     * RFC 6211 -  id-aa-cmsAlgorithmProtect OBJECT IDENTIFIER ::= {
     * iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1)
     * pkcs9(9) 52 }
     */
    ObjectIdentifier id_aa_cmsAlgorithmProtect = pkcs_9.branch("52").intern();

    //
    // SMIME capability sub oids.
    //
    /**
     * PKCS#9: 1.2.840.113549.1.9.15.1 -- smime capability
     */
    ObjectIdentifier preferSignedData = pkcs_9.branch("15.1");
    /**
     * PKCS#9: 1.2.840.113549.1.9.15.2 -- smime capability
     */
    ObjectIdentifier canNotDecryptAny = pkcs_9.branch("15.2");
    /**
     * PKCS#9: 1.2.840.113549.1.9.15.3 -- smime capability
     */
    ObjectIdentifier sMIMECapabilitiesVersions = pkcs_9.branch("15.3");

    //
    // id-ct OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) ct(1)}
    //
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1 -- smime ct
     */
    ObjectIdentifier id_ct = new ObjectIdentifier("1.2.840.113549.1.9.16.1");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1.2 -- smime ct authData
     */
    ObjectIdentifier id_ct_authData = id_ct.branch("2");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1.4 -- smime ct TSTInfo
     */
    ObjectIdentifier id_ct_TSTInfo = id_ct.branch("4");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1.9 -- smime ct compressedData
     */
    ObjectIdentifier id_ct_compressedData = id_ct.branch("9");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1.23 -- smime ct authEnvelopedData
     */
    ObjectIdentifier id_ct_authEnvelopedData = id_ct.branch("23");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.1.31 -- smime ct timestampedData
     */
    ObjectIdentifier id_ct_timestampedData = id_ct.branch("31");


    /**
     * S/MIME: Algorithm Identifiers ; 1.2.840.113549.1.9.16.3
     */
    ObjectIdentifier id_alg = id_smime.branch("3");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.3.9
     */
    ObjectIdentifier id_alg_PWRI_KEK = id_alg.branch("9");
    /**
     * <pre>
     * -- RSA-KEM Key Transport Algorithm  RFC 5990
     *
     * id-rsa-kem OID ::= {
     *      iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1)
     *      pkcs-9(9) smime(16) alg(3) 14
     *   }
     * </pre>
     */
    ObjectIdentifier id_rsa_KEM = id_alg.branch("14");

    //
    // id-cti OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) cti(6)}
    //
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6 -- smime cti
     */
    ObjectIdentifier id_cti = new ObjectIdentifier("1.2.840.113549.1.9.16.6");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.1 -- smime cti proofOfOrigin
     */
    ObjectIdentifier id_cti_ets_proofOfOrigin = id_cti.branch("1");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2 -- smime cti proofOfReceipt
     */
    ObjectIdentifier id_cti_ets_proofOfReceipt = id_cti.branch("2");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.3 -- smime cti proofOfDelivery
     */
    ObjectIdentifier id_cti_ets_proofOfDelivery = id_cti.branch("3");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.4 -- smime cti proofOfSender
     */
    ObjectIdentifier id_cti_ets_proofOfSender = id_cti.branch("4");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.5 -- smime cti proofOfApproval
     */
    ObjectIdentifier id_cti_ets_proofOfApproval = id_cti.branch("5");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.6 -- smime cti proofOfCreation
     */
    ObjectIdentifier id_cti_ets_proofOfCreation = id_cti.branch("6");

    //
    // id-aa OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
    // rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) attributes(2)}
    //
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2 - smime attributes
     */
    ObjectIdentifier id_aa = new ObjectIdentifier("1.2.840.113549.1.9.16.2");


    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.1 -- smime attribute receiptRequest
     */
    ObjectIdentifier id_aa_receiptRequest = id_aa.branch("1");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.4 - See <a href="http://tools.ietf.org/html/rfc2634">RFC 2634</a>
     */
    ObjectIdentifier id_aa_contentHint = id_aa.branch("4"); // See RFC 2634
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.5
     */
    ObjectIdentifier id_aa_msgSigDigest = id_aa.branch("5");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.10
     */
    ObjectIdentifier id_aa_contentReference = id_aa.branch("10");
    /*
     * id-aa-encrypKeyPref OBJECT IDENTIFIER ::= {id-aa 11}
     *
     */
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.11
     */
    ObjectIdentifier id_aa_encrypKeyPref = id_aa.branch("11");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.12
     */
    ObjectIdentifier id_aa_signingCertificate = id_aa.branch("12");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.47
     */
    ObjectIdentifier id_aa_signingCertificateV2 = id_aa.branch("47");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.7 - See <a href="http://tools.ietf.org/html/rfc2634">RFC 2634</a>
     */
    ObjectIdentifier id_aa_contentIdentifier = id_aa.branch("7"); // See RFC 2634

    /*
     * RFC 3126
     */
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.14 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_signatureTimeStampToken = id_aa.branch("14");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.15 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_sigPolicyId = id_aa.branch("15");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.16 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_commitmentType = id_aa.branch("16");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.17 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_signerLocation = id_aa.branch("17");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.18 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_signerAttr = id_aa.branch("18");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.19 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_otherSigCert = id_aa.branch("19");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.20 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_contentTimestamp = id_aa.branch("20");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.21 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_certificateRefs = id_aa.branch("21");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.22 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_revocationRefs = id_aa.branch("22");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.23 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_certValues = id_aa.branch("23");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.24 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_revocationValues = id_aa.branch("24");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.25 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_escTimeStamp = id_aa.branch("25");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.26 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_certCRLTimestamp = id_aa.branch("26");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.27 - <a href="http://tools.ietf.org/html/rfc3126">RFC 3126</a>
     */
    ObjectIdentifier id_aa_ets_archiveTimestamp = id_aa.branch("27");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.37 - <a href="https://tools.ietf.org/html/rfc4108#section-2.2.5">RFC 4108</a>
     */
    ObjectIdentifier id_aa_decryptKeyID = id_aa.branch("37");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.6.2.38 - <a href="https://tools.ietf.org/html/rfc4108#section-2.2.6">RFC 4108</a>
     */
    ObjectIdentifier id_aa_implCryptoAlgs = id_aa.branch("38");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.2.54 <a href="https://tools.ietf.org/html/rfc7030">RFC7030</a>
     */
    ObjectIdentifier id_aa_asymmDecryptKeyID = id_aa.branch("54");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.2.43   <a href="https://tools.ietf.org/html/rfc7030">RFC7030</a>
     */
    ObjectIdentifier id_aa_implCompressAlgs = id_aa.branch("43");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.2.40   <a href="https://tools.ietf.org/html/rfc7030">RFC7030</a>
     */
    ObjectIdentifier id_aa_communityIdentifiers = id_aa.branch("40");

    /**
     * @deprecated use id_aa_ets_sigPolicyId instead
     */
    ObjectIdentifier id_aa_sigPolicyId = id_aa_ets_sigPolicyId;
    /**
     * @deprecated use id_aa_ets_commitmentType instead
     */
    ObjectIdentifier id_aa_commitmentType = id_aa_ets_commitmentType;
    /**
     * @deprecated use id_aa_ets_signerLocation instead
     */
    ObjectIdentifier id_aa_signerLocation = id_aa_ets_signerLocation;
    /**
     * @deprecated use id_aa_ets_otherSigCert instead
     */
    ObjectIdentifier id_aa_otherSigCert = id_aa_ets_otherSigCert;

    /**
     * id-spq OBJECT IDENTIFIER ::= {iso(1) member-body(2) usa(840)
     * rsadsi(113549) pkcs(1) pkcs-9(9) smime(16) id-spq(5)}; <p>
     * 1.2.840.113549.1.9.16.5
     */
    final String id_spq = "1.2.840.113549.1.9.16.5";

    /**
     * SMIME SPQ URI:     1.2.840.113549.1.9.16.5.1
     */
    ObjectIdentifier id_spq_ets_uri = new ObjectIdentifier(id_spq + ".1");
    /**
     * SMIME SPQ UNOTICE: 1.2.840.113549.1.9.16.5.2
     */
    ObjectIdentifier id_spq_ets_unotice = new ObjectIdentifier(id_spq + ".2");

    //
    // pkcs-12 OBJECT IDENTIFIER ::= {
    //       iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) 12 }
    //
    /**
     * PKCS#12: 1.2.840.113549.1.12
     */
    ObjectIdentifier pkcs_12 = new ObjectIdentifier("1.2.840.113549.1.12");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1
     */
    ObjectIdentifier bagtypes = pkcs_12.branch("10.1");

    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.1
     */
    ObjectIdentifier keyBag = bagtypes.branch("1");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.2
     */
    ObjectIdentifier pkcs8ShroudedKeyBag = bagtypes.branch("2");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.3
     */
    ObjectIdentifier certBag = bagtypes.branch("3");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.4
     */
    ObjectIdentifier crlBag = bagtypes.branch("4");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.5
     */
    ObjectIdentifier secretBag = bagtypes.branch("5");
    /**
     * PKCS#12: 1.2.840.113549.1.12.10.1.6
     */
    ObjectIdentifier safeContentsBag = bagtypes.branch("6");

    /**
     * PKCS#12: 1.2.840.113549.1.12.1
     */
    ObjectIdentifier pkcs_12PbeIds = pkcs_12.branch("1");

    /**
     * PKCS#12: 1.2.840.113549.1.12.1.1
     */
    ObjectIdentifier pbeWithSHAAnd128BitRC4 = pkcs_12PbeIds.branch("1");
    /**
     * PKCS#12: 1.2.840.113549.1.12.1.2
     */
    ObjectIdentifier pbeWithSHAAnd40BitRC4 = pkcs_12PbeIds.branch("2");
    /**
     * PKCS#12: 1.2.840.113549.1.12.1.3
     */
    ObjectIdentifier pbeWithSHAAnd3_KeyTripleDES_CBC = pkcs_12PbeIds.branch("3");
    /**
     * PKCS#12: 1.2.840.113549.1.12.1.4
     */
    ObjectIdentifier pbeWithSHAAnd2_KeyTripleDES_CBC = pkcs_12PbeIds.branch("4");
    /**
     * PKCS#12: 1.2.840.113549.1.12.1.5
     */
    ObjectIdentifier pbeWithSHAAnd128BitRC2_CBC = pkcs_12PbeIds.branch("5");
    /**
     * PKCS#12: 1.2.840.113549.1.12.1.6
     */
    ObjectIdentifier pbeWithSHAAnd40BitRC2_CBC = pkcs_12PbeIds.branch("6");

    /**
     * PKCS#12: 1.2.840.113549.1.12.1.6
     *
     * @deprecated use pbeWithSHAAnd40BitRC2_CBC
     */
    ObjectIdentifier pbewithSHAAnd40BitRC2_CBC = pkcs_12PbeIds.branch("6");

    /**
     * PKCS#9: 1.2.840.113549.1.9.16.3.6
     */
    ObjectIdentifier id_alg_CMS3DESwrap = new ObjectIdentifier("1.2.840.113549.1.9.16.3.6");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.3.7
     */
    ObjectIdentifier id_alg_CMSRC2wrap = new ObjectIdentifier("1.2.840.113549.1.9.16.3.7");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.3.5
     */
    ObjectIdentifier id_alg_ESDH = new ObjectIdentifier("1.2.840.113549.1.9.16.3.5");
    /**
     * PKCS#9: 1.2.840.113549.1.9.16.3.10
     */
    ObjectIdentifier id_alg_SSDH = new ObjectIdentifier("1.2.840.113549.1.9.16.3.10");
}

