package com.sfs.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt令牌操作的测试类
 *
 * @author yjw
 * @date 2020/5/4 23:23
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    /**
     * 创建jwt令牌
     */
    @Test
    public void testCreateJwt() {
        //密钥库文件
        String keystore = "sfs.keystore";
        //密钥库密码
        String keystore_pwd = "sfskeystore";
        //密钥库文件路径
        ClassPathResource classPathResource = new ClassPathResource(keystore);
        //密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keystore_pwd.toCharArray());

        //密钥的密码
        String keypwd = "sfspwd";
        //密钥的别名
        String alias = "sfskey";
        //密钥对(包含私钥和公钥)
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, keypwd.toCharArray());
        //从密钥对中获得私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        //定义自定义信息
        Map<String, String> map = new HashMap<>();
        map.put("id", "123456");
        map.put("name", "从零开始");
        //根据私钥生成jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));

        String token = jwt.getEncoded();
        System.out.println("token=" + token);
    }

    /**
     * 校验jwt令牌,解析jwt令牌获取内容
     */
    @Test
    public void testVerify() {
        //jwt令牌内容
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55SWQiOiIxIiwidXNlcnBpYyI6bnVsbCwidXNlcl9uYW1lIjoiaXRjYXN0Iiwic2NvcGUiOlsiYXBwIl0sIm5hbWUiOiJ0ZXN0MDIiLCJ1dHlwZSI6IjEwMTAwMiIsImlkIjoiNDkiLCJleHAiOjE1ODkxNTU3NDUsImF1dGhvcml0aWVzIjpbInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfYmFzZSIsInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfZGVsIiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9saXN0IiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9wbGFuIiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZSIsImNvdXJzZV9maW5kX2xpc3QiLCJ4Y190ZWFjaG1hbmFnZXIiLCJ4Y190ZWFjaG1hbmFnZXJfY291cnNlX21hcmtldCIsInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfcHVibGlzaCIsInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfYWRkIl0sImp0aSI6ImJjYjJlOTNiLTZkY2QtNGZjOS05YTNhLTA5YWI0MjRkNWRmNCIsImNsaWVudF9pZCI6IlhjV2ViQXBwIn0.CxpyaKAgLvq6xNGMTejHsbh2yIIW2hav8KmMHtBdq5KhT1Xi8SCwO7A3WBIxhRgQfVG5QAfmpW6M0bsK7ucgF5SAt8GcTnJznl3GUaa-1NRiip4E-VZwmjUZdJ2TyT5_BijmDypVW8deQOSXtFCGNCP9ZOCCUEYB8VbsTTGJuBKKpkSv29SrVkPaSb83AK071GYGtnkHTI6n2C07BzZnePKCcmXbb9LIGAsyyHM2ao77ZtiaMtcxutmKH7G3palcUFrRTCsYARFhorMOUTmcQO53afI-_WQSZY2qFiDRrYnHcJzq8Z9eqt-aLxO6mBRkVOznWOA5V_EyOo4e5n8d4g";
        //公钥内容
        String key_public = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoK7CBngzicZwQoR1fYl4aRyG/lOBbquo8IP5K6+VRks9g35+BRljn575W6/o3nA8Xj8RIHfFkr0g6cU3EkGaUgI4TfIAT3KMaG4gQWeYI5vy2vmSFkbwtBvTx//gxSJvfotwscb7gV3VDzj7aGLyZGE/vihxNapR3FyVIQDDViAgebSmKCzksADf+Zv09S2MTX1kNup8MDMx8193wtfKUhjjm4PvjzJR5mMxrFZOJSImhjIwjyKyP7fTkulMm8YY0+/m38cwQsAuPgKRB7oHFJiFac0ZzG1MMAEa5MtPA6/2vusCR3EolHOsYdCtOHB4wy4Anx/nPHa0IO6j9BZGCQIDAQAB-----END PUBLIC KEY-----";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(key_public));
        //获取jwt原始内容(设置的自定义内容)
        String claims = jwt.getClaims();
        System.out.println(""+claims);
        //获取jwt令牌内容
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }


}
