package com.wxmlabs.oid;

/**
 * GB/T 33560-2017 《信息安全技术 密码应用标识规范》
 * <i>Information security technology—Cryptographic application identifier criterion specification</i>
 * <p>
 * 王翾旻 2018年10月14日
 */
public interface GB33560ObjectIdentifiers {
    ObjectIdentifier id_cstc = new ObjectIdentifier("1.2.156.10197"); // 国家密码行业标准化技术委员会
    ObjectIdentifier id_gm_algorithm = id_cstc.branch("1");

    ObjectIdentifier id_gm_symmetric = id_gm_algorithm.branch("100");
    ObjectIdentifier sm1 = id_gm_algorithm.branch("102");
    ObjectIdentifier ssf33 = id_gm_algorithm.branch("103");
    ObjectIdentifier sm4 = id_gm_algorithm.branch("104");
    ObjectIdentifier id_gm_stream = id_gm_algorithm.branch("200");
    ObjectIdentifier zuc = id_gm_algorithm.branch("201");

    ObjectIdentifier id_gm_asymmetric = id_gm_algorithm.branch("300");
    ObjectIdentifier sm2 = id_gm_algorithm.branch("301");
    ObjectIdentifier sm2_1 = sm2.branch("1"); // SM2签名算法
    ObjectIdentifier sm2_2 = sm2.branch("2"); // SM2密钥交换协议
    ObjectIdentifier sm2_3 = sm2.branch("3"); // SM2加密算法
    ObjectIdentifier sm9 = id_gm_algorithm.branch("302");
    ObjectIdentifier sm9_1 = sm9.branch("1"); // SM9签名算法
    ObjectIdentifier sm9_2 = sm9.branch("2"); // SM9密钥交换协议
    ObjectIdentifier sm9_3 = sm9.branch("3"); // SM9密钥封装机制和公钥加密算法

    ObjectIdentifier id_gm_digest = id_gm_algorithm.branch("400");
    ObjectIdentifier sm3 = id_gm_algorithm.branch("401");
    ObjectIdentifier sm3_1 = sm3.branch("1"); // SM3杂凑算法，无密钥使用
    ObjectIdentifier sm3_2 = sm3.branch("2"); // SM3杂凑算法，有密钥使用

    ObjectIdentifier id_gm_digest_signature = id_gm_algorithm.branch("500");
    ObjectIdentifier sm3_with_sm2 = id_gm_algorithm.branch("501");
    ObjectIdentifier sm3_with_sm9 = id_gm_algorithm.branch("502");
    ObjectIdentifier sm3_with_rsa = id_gm_algorithm.branch("504");

    ObjectIdentifier id_ca = id_cstc.branch("4.3");

    ObjectIdentifier id_gm_standard_scheme = id_cstc.branch("6");
    ObjectIdentifier id_gm_standard_basic = id_gm_standard_scheme.branch("1");
    ObjectIdentifier id_gm_security_mechanism = id_gm_standard_basic.branch("4");
}
