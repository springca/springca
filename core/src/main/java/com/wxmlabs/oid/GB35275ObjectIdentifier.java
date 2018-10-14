package com.wxmlabs.oid;

/**
 * GB/T 35275-2017 《信息安全技术 SM2密码算法加密签名消息语法规范》
 * <i>Information security technology—SM2 cryptographic algorithm encrypted signature message syntax specification</i>
 * <p>
 * 王翾旻 2018年10月14日
 */
public interface GB35275ObjectIdentifier {
    ObjectIdentifier id_cms_sm2 = new ObjectIdentifier("1.2.10197.2"); // SM2加密签名消息语法规范
    ObjectIdentifier cms_sm2_data = id_cms_sm2.branch("1");
    ObjectIdentifier cms_sm2_signedData = id_cms_sm2.branch("2");
    ObjectIdentifier cms_sm2_envelopedData = id_cms_sm2.branch("3");
    ObjectIdentifier cms_sm2_signedAndEnvelopedData = id_cms_sm2.branch("4");
    ObjectIdentifier cms_sm2_encryptedData = id_cms_sm2.branch("5");
    ObjectIdentifier cms_sm2_keyAgreementInfo = id_cms_sm2.branch("6");
}
