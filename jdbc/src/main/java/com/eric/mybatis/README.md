# ph_eric
 Eric‘s home



编码中的报错：
问题：Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
原因：MySQL在高版本需要指明是否进行SSL连接。
解决方案：在mysql连接字符串url中加入ssl=true或者false即可。

问题：springboot2集成hikari连接池的一些坑，Driver does not support get/set network timeout for connections.
原因：驱动版本不匹配
解决方案：mysql驱动版本升级至5.1.47