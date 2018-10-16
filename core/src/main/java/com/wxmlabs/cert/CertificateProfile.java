package com.wxmlabs.cert;

import com.wxmlabs.springca.util.CertUtil;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.CertificatePolicies;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyConstraints;
import org.bouncycastle.asn1.x509.PolicyMappings;
import org.bouncycastle.asn1.x509.PrivateKeyUsagePeriod;
import org.bouncycastle.asn1.x509.SubjectDirectoryAttributes;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.operator.DigestCalculator;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;

/**
 * 用于解析规则文件certficate-profile.xml
 *
 * 参考标准 RFC 5280
 * 参考标准 GB/T 20518
 *
 * profile文件表述了一个证书的签发条件。
 * 默认为必选项，即该项必须存在，且按满足其规则要求；
 * "DISABLED"项为禁用项，所含项禁止使用；
 * "OPTIONAL"为可选项，但"OPTIONAL"项存在时，其内部结构必须满足其规则定义；
 * 未在profile中声明的项不做规则检查。
 *
 * 后续考虑支持各种条件规则。
 *
 * 王翾旻 2018年10月14日
 */
public class CertificateProfile {
    /**
     * 版本号
     * 无需赋值。要求使用第三版本，固定值2。
     */
    private final int version = 2;
    /**
     * 序列号
     * 必须为正整数。
     */
    private BigInteger serialNumber;
    /**
     * 签名算法
     * 无需手动赋值。RSA密钥固定为"SHA256withRSA"，SM2密钥固定为"SM3withSM2"。
     */
    private String signature;
    /**
     * 颁发者
     */
    private X500Principal issuer;
    /**
     * 有效日期
     */
    private Date notBefore;
    private Date notAfter;
    /**
     * 主体
     */
    private X500Principal subject;
    /**
     * 主体公钥
     * RSA密钥长度必须为2048位及以上，SM2密钥长度必须256位及以上。
     */
    private PublicKey publicKey;
    /**
     * 颁发机构密钥标识符（非关键）
     * 无需手动赋值。从颁发者证书中自动获取。自签名证书与subjectKeyIdentifier一致。
     */
    private AuthorityKeyIdentifier authorityKeyIdentifier;

    /**
     * 主体密钥标识符（非关键）
     * 无需手动赋值。根据publicKey自动计算。
     */
    private SubjectKeyIdentifier subjectKeyIdentifier;

    /**
     * 密钥用法（双证书标记为关键，单证书标记为非关键）
     */
    private KeyUsage keyUsage;
    /**
     * 扩展密钥用途（如果密钥用法只限于所指的用途时标记为关键，否则标记为非关键）
     */
    private ExtendedKeyUsage extKeyUsage;
    /**
     * 私有密钥使用期（非关键）
     */
    private PrivateKeyUsagePeriod privateKeyUsagePeriod;
    /**
     * 证书策略（非关键）
     */
    private CertificatePolicies certificatePolicies;
    /**
     * 策略映射（如果证书用户需要正确的解释CA的发布设定的规则时标识为关键，否则标识为非关键）
     */
    private PolicyMappings policyMappings;
    /**
     * 主体可选替换名称（非关键）
     */
    private Extension subjectAlgName;
    /**
     * 颁发者可换替换名称（非关键）
     */
    private Extension issuerAltName;
    /**
     * 主体目录属性（非关键）
     */
    private SubjectDirectoryAttributes subjectDirectoryAttributes;
    /**
     * 基本限制（CA证书标记为关键，终端实体证书标记为非关键）
     */
    private BasicConstraints basicConstraints;
    /**
     * 名称限制（如果证书用户系统应检验所处理的认证路径与此扩展中的值是否一致时标记为关键，否则标记为非关键）
     */
    private NameConstraints nameConstraints;
    /**
     * 策略限制（如果证书用户需要正确的解释认证机构CA设定的规则时标识为关键，否则标识为非关键）
     */
    private PolicyConstraints policyConstraints;
    /**
     * CRL分发点（非关键）
     */
    private CRLDistPoint cRLDistributionPoints;
    /**
     * 限制所有策略（如果证书用户需要正确的解释认证机构CA设定的规则时标识为关键，否则标识为非关键）
     */
    private Extension inhibitAnyPolicy;
    /**
     * 最新的CRL（非关键）
     */
    private Extension freshestCRL;
    /**
     * 私有的Internet扩展（非关键）
     */
    private Extension id_pkix;
    /**
     * 颁发机构信息访问（非关键）
     */
    private AuthorityInformationAccess authorityInfoAccess;
    /**
     * 主体信息访问（非关键）
     */
    private Extension subjectInformationAccess;
    /**
     * 个人身份证号码（非关键）
     */
    private Extension identifyCardNumber;
    /**
     * 个人社会保险号（非关键）
     */
    private Extension inuranceNumber;
    /**
     * 企业工商注册号（非关键）
     */
    private Extension iCRegistrationNumber;
    /**
     * 企业组织机构代码（非关键）
     */
    private Extension organizationCode;
    /**
     * 企业税号（非关键）
     */
    private Extension taxationNumber;

    public int getVersion() {
        return version;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public CertificateProfile setSerialNumber(BigInteger serialNumber) {
        checkSerialNumber(serialNumber);
        this.serialNumber = serialNumber;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public X500Principal getIssuer() {
        if (issuer == null && selfSign) {
            issuer = subject;
        }
        return issuer;
    }

    /**
     * 设置自签名证书颁发者
     *
     * @return
     */
    public CertificateProfile setSelfSignIssuer(PrivateKey issuerKey) {
        try {
            setIssuer(null, issuerKey);
        } catch (CertificateEncodingException ignore) {
        }
        return this;
    }

    public CertificateProfile setIssuer(X509Certificate issuerCert, PrivateKey issuerKey) throws CertificateEncodingException {
        if (issuerCert == null) {
            this.selfSign = true;
            this.issuer = this.subject;
            // 自签名根证书不需要authorityKeyIdentifier
        } else {
            JcaX509CertificateHolder issuerHolder = new JcaX509CertificateHolder(issuerCert);
            this.issuer = issuerCert.getSubjectX500Principal();
            this.authorityKeyIdentifier = AuthorityKeyIdentifier.fromExtensions(issuerHolder.getExtensions());
            if (authorityKeyIdentifier == null) {
                this.authorityKeyIdentifier = extensionUtils.createAuthorityKeyIdentifier(issuerHolder.getSubjectPublicKeyInfo());
            }
        }
        this.signature = CertUtil.defaultSigAlg(issuerKey);
        return this;
    }


    public Date getNotBefore() {
        return notBefore;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public CertificateProfile setValidity(Date notBefore, Date notAfter) {
        checkValidity(notBefore, notAfter);
        this.notBefore = notBefore;
        this.notAfter = notAfter;
        return this;
    }

    public X500Principal getSubject() {
        return subject;
    }

    public CertificateProfile setSubject(X500Principal subject, PublicKey publicKey) {
        this.subject = subject;
        this.publicKey = publicKey;
        this.subjectKeyIdentifier = extensionUtils.createSubjectKeyIdentifier(publicKey);
        return this;
    }

    public CertificateProfile setSubject(String country, String province, String city, String organization, String orgUnit, String commonName, PublicKey publicKey) {
        String subjectName = String.format("CN=%s, OU=%s, O=%s, C=%s", commonName, orgUnit, organization, country);
        setSubject(new X500Principal(subjectName), publicKey);
        return this;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public AuthorityKeyIdentifier getAuthorityKeyIdentifier() {
        if (authorityKeyIdentifier == null && selfSign && subjectKeyIdentifier != null) {
            authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(subjectKeyIdentifier.getKeyIdentifier());
        }
        return authorityKeyIdentifier;
    }

    public SubjectKeyIdentifier getSubjectKeyIdentifier() {
        return subjectKeyIdentifier;
    }

    public KeyUsage getKeyUsage() {
        return keyUsage;
    }

    public static final int digitalSignature = (1 << 7);
    public static final int nonRepudiation = (1 << 6);
    public static final int keyEncipherment = (1 << 5);
    public static final int dataEncipherment = (1 << 4);
    public static final int keyAgreement = (1 << 3);
    public static final int keyCertSign = (1 << 2);
    public static final int cRLSign = (1 << 1);
    public static final int encipherOnly = (1 << 0);
    public static final int decipherOnly = (1 << 15);

    public CertificateProfile setKeyUsage(int keyUsage) {
        this.keyUsage = new KeyUsage(keyUsage);
        return this;
    }

    public ExtendedKeyUsage getExtKeyUsage() {
        return extKeyUsage;
    }

    public CertificateProfile setExtKeyUsage(Collection<String> keyPurposeIds) {
        if (keyPurposeIds != null) {
            KeyPurposeId[] extKeyUsage = new KeyPurposeId[keyPurposeIds.size()];
            int i = 0;
            for (String kp : keyPurposeIds) {
                extKeyUsage[i] = KeyPurposeId.getInstance(kp);
            }
            this.extKeyUsage = new ExtendedKeyUsage(extKeyUsage);
        }
        return this;
    }

    public PrivateKeyUsagePeriod getPrivateKeyUsagePeriod() {
        return privateKeyUsagePeriod;
    }

    // TODO 分割线，从这里继续
    public CertificateProfile setPrivateKeyUsagePeriod(PrivateKeyUsagePeriod privateKeyUsagePeriod) {
        this.privateKeyUsagePeriod = privateKeyUsagePeriod;
        return this;
    }

    public CertificatePolicies getCertificatePolicies() {
        return certificatePolicies;
    }

    public CertificateProfile setCertificatePolicies(CertificatePolicies certificatePolicies) {
        this.certificatePolicies = certificatePolicies;
        return this;
    }

    public PolicyMappings getPolicyMappings() {
        return policyMappings;
    }

    public CertificateProfile setPolicyMappings(PolicyMappings policyMappings) {
        this.policyMappings = policyMappings;
        return this;
    }

    public Extension getSubjectAlgName() {
        return subjectAlgName;
    }

    public CertificateProfile setSubjectAlgName(Extension subjectAlgName) {
        this.subjectAlgName = subjectAlgName;
        return this;
    }

    public Extension getIssuerAltName() {
        return issuerAltName;
    }

    public CertificateProfile setIssuerAltName(Extension issuerAltName) {
        this.issuerAltName = issuerAltName;
        return this;
    }

    public SubjectDirectoryAttributes getSubjectDirectoryAttributes() {
        return subjectDirectoryAttributes;
    }

    public CertificateProfile setSubjectDirectoryAttributes(SubjectDirectoryAttributes subjectDirectoryAttributes) {
        this.subjectDirectoryAttributes = subjectDirectoryAttributes;
        return this;
    }

    public BasicConstraints getBasicConstraints() {
        return basicConstraints;
    }

    public CertificateProfile setBasicConstraints(BasicConstraints basicConstraints) {
        this.basicConstraints = basicConstraints;
        return this;
    }

    public NameConstraints getNameConstraints() {
        return nameConstraints;
    }

    public CertificateProfile setNameConstraints(NameConstraints nameConstraints) {
        this.nameConstraints = nameConstraints;
        return this;
    }

    public PolicyConstraints getPolicyConstraints() {
        return policyConstraints;
    }

    public CertificateProfile setPolicyConstraints(PolicyConstraints policyConstraints) {
        this.policyConstraints = policyConstraints;
        return this;
    }

    public CRLDistPoint getcRLDistributionPoints() {
        return cRLDistributionPoints;
    }

    public CertificateProfile setcRLDistributionPoints(CRLDistPoint cRLDistributionPoints) {
        this.cRLDistributionPoints = cRLDistributionPoints;
        return this;
    }

    public Extension getInhibitAnyPolicy() {
        return inhibitAnyPolicy;
    }

    public CertificateProfile setInhibitAnyPolicy(Extension inhibitAnyPolicy) {
        this.inhibitAnyPolicy = inhibitAnyPolicy;
        return this;
    }

    public Extension getFreshestCRL() {
        return freshestCRL;
    }

    public CertificateProfile setFreshestCRL(Extension freshestCRL) {
        this.freshestCRL = freshestCRL;
        return this;
    }

    public Extension getId_pkix() {
        return id_pkix;
    }

    public CertificateProfile setId_pkix(Extension id_pkix) {
        this.id_pkix = id_pkix;
        return this;
    }

    public AuthorityInformationAccess getAuthorityInfoAccess() {
        return authorityInfoAccess;
    }

    public CertificateProfile setAuthorityInfoAccess(AuthorityInformationAccess authorityInfoAccess) {
        this.authorityInfoAccess = authorityInfoAccess;
        return this;
    }

    public Extension getSubjectInformationAccess() {
        return subjectInformationAccess;
    }

    public CertificateProfile setSubjectInformationAccess(Extension subjectInformationAccess) {
        this.subjectInformationAccess = subjectInformationAccess;
        return this;
    }

    public Extension getIdentifyCardNumber() {
        return identifyCardNumber;
    }

    public CertificateProfile setIdentifyCardNumber(Extension identifyCardNumber) {
        this.identifyCardNumber = identifyCardNumber;
        return this;
    }

    public Extension getInuranceNumber() {
        return inuranceNumber;
    }

    public CertificateProfile setInuranceNumber(Extension inuranceNumber) {
        this.inuranceNumber = inuranceNumber;
        return this;
    }

    public Extension getiCRegistrationNumber() {
        return iCRegistrationNumber;
    }

    public CertificateProfile setiCRegistrationNumber(Extension iCRegistrationNumber) {
        this.iCRegistrationNumber = iCRegistrationNumber;
        return this;
    }

    public Extension getOrganizationCode() {
        return organizationCode;
    }

    public CertificateProfile setOrganizationCode(Extension organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public Extension getTaxationNumber() {
        return taxationNumber;
    }

    public CertificateProfile setTaxationNumber(Extension taxationNumber) {
        this.taxationNumber = taxationNumber;
        return this;
    }

    private void checkSerialNumber(BigInteger serialNumber) {
        if (serialNumber.signum() < 1) {
            throw new IllegalArgumentException("'serialNumber' must be positive.");
        }
    }

    private void checkValidity(Date notBefore, Date notAfter) {
        if (notAfter.before(notBefore)) {
            throw new IllegalArgumentException("''");
        }
    }

    private boolean selfSign = false;
    private PrivateKey issuerKey;
    private JcaX509ExtensionUtils extensionUtils = new JcaX509ExtensionUtils(new SHA1DigestCalculator());

    private static class SHA1DigestCalculator
        implements DigestCalculator {
        private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }

        public OutputStream getOutputStream() {
            return bOut;
        }

        public byte[] getDigest() {
            byte[] bytes = bOut.toByteArray();

            bOut.reset();

            Digest sha1 = new SHA1Digest();

            sha1.update(bytes, 0, bytes.length);

            byte[] digest = new byte[sha1.getDigestSize()];

            sha1.doFinal(digest, 0);

            return digest;
        }
    }
}
