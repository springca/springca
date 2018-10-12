package com.wxmlabs.gm;

/**
 * GB/T 33560-2017 《信息安全技术 密码应用标识规范》
 * <i>Information security technology—Cryptographic application identifier criterion specification*</i>
 * <p>
 * GB/T 35275-2017 《信息安全技术 SM2密码算法加密签名消息语法规范》
 * <i>Information security technology—SM2 cryptographic algorithm encrypted signature message syntax specification</i>
 * <p>
 * 使用了org.bouncycastle.asn1.ASN1ObjectIdentifier中对OID处理的方法。
 */
public class OID {
    // GB/T 33560-2017
    public static final OID id_iso = new OID("1.2"); // 国际标准化组织
    public static final OID id_cn = id_iso.branch("156"); // 中国
    public static final OID id_sca = id_cn.branch("197"); // 国家密码管理局
    public static final OID id_cstc = id_cn.branch("10197"); // 国家密码行业标准化技术委员会
    public static final OID id_gm_algorithm = id_cstc.branch("1"); // 密码算法

    public static final OID id_gm_symmetric = id_gm_algorithm.branch("100");
    public static final OID sm1 = id_gm_algorithm.branch("102");
    public static final OID ssf33 = id_gm_algorithm.branch("103");
    public static final OID sm4 = id_gm_algorithm.branch("104");
    public static final OID id_gm_stream = id_gm_algorithm.branch("200");
    public static final OID zuc = id_gm_algorithm.branch("201");

    public static final OID id_gm_asymmetric = id_gm_algorithm.branch("300");
    public static final OID sm2 = id_gm_algorithm.branch("301");
    public static final OID sm2_1 = sm2.branch("1"); // SM2签名算法
    public static final OID sm2_2 = sm2.branch("2"); // SM2密钥交换协议
    public static final OID sm2_3 = sm2.branch("3"); // SM2加密算法
    public static final OID sm9 = id_gm_algorithm.branch("302");
    public static final OID sm9_1 = sm9.branch("1"); // SM9签名算法
    public static final OID sm9_2 = sm9.branch("2"); // SM9密钥交换协议
    public static final OID sm9_3 = sm9.branch("3"); // SM9密钥封装机制和公钥加密算法

    public static final OID id_gm_digest = id_gm_algorithm.branch("400");
    public static final OID sm3 = id_gm_algorithm.branch("401");
    public static final OID sm3_1 = sm3.branch("1"); // SM3杂凑算法，无密钥使用
    public static final OID sm3_2 = sm3.branch("2"); // SM3杂凑算法，有密钥使用

    public static final OID id_gm_digest_signature = id_gm_algorithm.branch("500");
    public static final OID sm3_with_sm2 = id_gm_algorithm.branch("501");
    public static final OID sm3_with_sm9 = id_gm_algorithm.branch("502");
    public static final OID sm3_with_rsa = id_gm_algorithm.branch("504");

    public static final OID id_ca = id_cstc.branch("4.3");

    public static final OID id_gm_standard_scheme = id_cstc.branch("6");
    public static final OID id_gm_standard_basic = id_gm_standard_scheme.branch("1");
    public static final OID id_gm_security_mechanism = id_gm_standard_basic.branch("4");

    // GB/T 35275-2017
    public static final OID id_cms_sm2 = id_gm_security_mechanism.branch("2"); // SM2加密签名消息语法规范
    public static final OID cms_sm2_data = id_cms_sm2.branch("1");
    public static final OID cms_sm2_signedData = id_cms_sm2.branch("2");
    public static final OID cms_sm2_envelopedData = id_cms_sm2.branch("3");
    public static final OID cms_sm2_signedAndEnvelopedData = id_cms_sm2.branch("4");
    public static final OID cms_sm2_encryptedData = id_cms_sm2.branch("5");
    public static final OID cms_sm2_keyAgreementInfo = id_cms_sm2.branch("6");


    private final String identifier;

    public OID(String identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        }
        if (!isValidIdentifier(identifier)) {
            throw new IllegalArgumentException("string " + identifier + " not an OID");
        }
        this.identifier = identifier;
    }

    OID(OID oid, String branchId) {
        this.identifier = oid + "." + branchId;
    }


    public OID branch(String branchId) {
        return new OID(this, branchId);
    }

    @Override
    public String toString() {
        return identifier;
    }


    private static boolean isValidBranchID(String branchID, int start) {
        boolean periodAllowed = false;

        int pos = branchID.length();
        while (--pos >= start) {
            char ch = branchID.charAt(pos);

            // TODO Leading zeroes?
            if ('0' <= ch && ch <= '9') {
                periodAllowed = true;
                continue;
            }

            if (ch == '.') {
                if (!periodAllowed) {
                    return false;
                }

                periodAllowed = false;
                continue;
            }

            return false;
        }

        return periodAllowed;
    }

    private static boolean isValidIdentifier(String identifier) {
        if (identifier.length() < 3 || identifier.charAt(1) != '.') {
            return false;
        }

        char first = identifier.charAt(0);
        if (first < '0' || first > '2') {
            return false;
        }

        return isValidBranchID(identifier, 2);
    }
}
