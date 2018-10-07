# Roadmap

### v0.1

- 基于[Java Cryptography Architecture][]作为基础体系，可延伸支持所有实现了标准JCA的密钥设备
- 单一颁发者
- 支持 RFC 5967 (PKCS10 Certificate Request)

[Java Cryptography Architecture]: https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html

### 0.2 

- Web API
- 支持SM2算法
- 根据中国国家标准调整证书轮廓


### Features

- 支持双证书（签名、加密用途分离）签发
- 访问授权
- 多颁发者
- 颁发者(Issuer)可配置多种算法的签发密钥，根据证书请求使用对应算法的密钥进行证书签发
