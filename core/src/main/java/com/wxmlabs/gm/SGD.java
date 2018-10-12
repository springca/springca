package com.wxmlabs.gm;

/**
 * GB/T 33560-2017 《信息安全技术 密码应用标识规范》
 * <i>Information security technology—Cryptographic application identifier criterion specification*</i>
 */
public abstract class SGD {
    // Symmetric 分组密码算法标识
    public static final long SGD_SM1_ECB = 0x00000101;
    public static final long SGD_SM1_CBC = 0x00000102;
    public static final long SGD_SM1_CFB = 0x00000104;
    public static final long SGD_SM1_OFB = 0x00000108;
    public static final long SGD_SSF33_ECB = 0x00000201;
    public static final long SGD_SSF33_CBC = 0x00000202;
    public static final long SGD_SSF33_CFB = 0x00000204;
    public static final long SGD_SSF33_OFB = 0x00000208;
    public static final long SGD_SSF33_MAC = 0x00000210;
    public static final long SGD_SM4_ECB = 0x00000401;
    public static final long SGD_SM4_CBC = 0x00000402;
    public static final long SGD_SM4_CFB = 0x00000404;
    public static final long SGD_SM4_OFB = 0x00000408;
    public static final long SGD_SM4_MAC = 0x00000410;
    public static final long SGD_ZUC_EEA3 = 0x00000801;
    public static final long SGD_ZUC_EIA3 = 0x00000802;

    // Asymmetric 非对称密码算法标识
    public static final long SGD_RSA = 0x00010000; // RSA算法
    public static final long SGD_SM2 = 0x00020100; // SM2密码算法
    public static final long SGD_SM2_1 = 0x00020200; // SM2签名算法
    public static final long SGD_SM2_2 = 0x00020400; // SM2密钥交换协议
    public static final long SGD_SM2_3 = 0x00020800; // SM2加密算法
    public static final long SGD_SM9 = 0x00040100; // SM9密码算法
    public static final long SGD_SM9_1 = 0x00040200; // SM9签名算法
    public static final long SGD_SM9_2 = 0x00040400; // SM9密钥交换协议
    public static final long SGD_SM9_3 = 0x00040800; // SM9密钥封装机制和公钥加密算法

    // Digest 密码杂凑算法标识
    public static final long SGD_SM3 = 0x00000001;
    public static final long SGD_SHA1 = 0x00000002;
    public static final long SGD_SHA256 = 0x00000004;

    // 签名算法标识
    public static final long SGD_SM3_RSA = SGD_SM3 | SGD_RSA; // SM3withRSA
    public static final long SGD_SHA1_RSA = SGD_SHA1 | SGD_RSA; // SHA1withRSA
    public static final long SGD_SHA256_RSA = SGD_SHA256 | SGD_RSA; // SHA256withRSA
    public static final long SGD_SM3_SM2 = SGD_SM3 | SGD_SM2_1; // SM3withSM2
    public static final long SGD_SM3_SM9 = SGD_SM3 | SGD_SM9_1; // SM3withSM9

    // 数据编码格式
    public static final long SGD_ENCODING_RAW = 0x00000000; // 无编码
    public static final long SGD_ENCODING_DER = 0x01000000; // DER编码
    public static final long SGD_ENCODING_BASE64 = 0x02000000; // Base64编码
    public static final long SGD_ENCODING_PEM = 0x03000000; // PEM编码
    public static final long SGD_ENCODING_TXT = 0x04000000; // 十六进制编码 [0-9A-Z]
}
