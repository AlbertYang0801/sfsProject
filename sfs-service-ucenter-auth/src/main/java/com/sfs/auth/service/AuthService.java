package com.sfs.auth.service;

import com.alibaba.fastjson.JSON;
import com.sfs.framework.client.SfsServiceList;
import com.sfs.framework.domain.ucenter.SfsUser;
import com.sfs.framework.domain.ucenter.ext.AuthToken;
import com.sfs.framework.domain.ucenter.response.AuthCode;
import com.sfs.framework.exception.ExceptionCast;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 认证服务
 *
 * @author yjw
 * @date 2020/5/5 21:14
 */
@Service
public class AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    /**
     * token存储到redis的过期时间
     */
    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;

    /**
     * 认证方法
     *
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     */
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //根据用户密码申请令牌
        AuthToken token = getToken(username, password, clientId, clientSecret);
        if (token == null) {
            //提示认证过程申请令牌出错
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_ERROR);
        }
        //jwt令牌标识信息作为key值
        String jwt_token = token.getJwt_token();
        //jwt令牌全部内容
        String tokenString = JSON.toJSONString(token);
        //将jwt令牌身份信息保存到redis数据库
        boolean b = saveTokenToRedis(jwt_token, tokenString, tokenValiditySeconds);
        if (!b) {
            //提示认证过程令牌保存数据库失败
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_SAVEFAIL);
        }
        return token;
    }

    /**
     * 根据标识令牌删除redis中的jwt令牌
     *
     * @param jti_token
     * @return
     */
    public boolean deleteToken(String jti_token) {
        String key = "user_token:" + jti_token;
        return stringRedisTemplate.delete(key);
    }

    /**
     * 根据标识内容从redis获取jwt令牌内容
     *
     * @param jti
     * @return
     */
    public AuthToken getUserToken(String jti) {
        //设置令牌标识内容，拼接数据key
        String userToken = "user_token:" + jti;
        //根据标识内容拼接的key从redis数据库查询jwt令牌内容
        String userTokenString = stringRedisTemplate.opsForValue().get(userToken);
        AuthToken authToken = null;
        if (userTokenString != null) {
            //将令牌字符串转换为令牌实体类
            authToken = JSON.parseObject(userTokenString, AuthToken.class);
        }
        return authToken;
    }


//************************************************************************************************************

    /**
     * 申请令牌
     *
     * @param username     用户名
     * @param password     密码
     * @param clientId     客户端id
     * @param clientSecret 客户端密码
     * @return
     */
    private AuthToken getToken(String username, String password, String clientId, String clientSecret) {

        //从eureka中获取认证服务的ip地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(SfsServiceList.SFS_SERVICE_UCENTER_AUTH);
        URI uri = serviceInstance.getUri();
        //设置令牌申请的地址（http://localhost:40400/auth/oauth/token）
        String authUrl = uri + "/auth/oauth/token";

        //设置头部内容
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        //根据客户端id和客户端密码获取basci字符串
        String httpBasic = getHttpBasic(clientId, clientSecret);
        headers.add("Authorization", httpBasic);

        //设置用户信息
        LinkedMultiValueMap<String, String> bodys = new LinkedMultiValueMap<>();
        //设置授权模式为密码模式
        bodys.add("grant_type", "password");
        bodys.add("username", username);
        bodys.add("password", password);

        HttpEntity<MultiValueMap<String, String>> multiValueMapHttpEntity = new HttpEntity<>(bodys, headers);

        //设置远程请求出现异常，报400或401，正常返回内容
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        //远程请求调用申请令牌
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, multiValueMapHttpEntity, Map.class);
        //获取令牌内容
        Map map = exchange.getBody();
        if (map == null || map.get("access_token") == null
                || map.get("refresh_token") == null || map.get("jti") == null) {
            //获取spring security返回的错误信息
            String error_description = (String) map.get("error_description");
            if (StringUtils.isNotEmpty(error_description)) {
                if (error_description.equals("坏的凭证")) {
                    //提示账号或密码错误
                    ExceptionCast.cast(AuthCode.AUTH_CREDENTIAL_ERROR);
                } else if (error_description.indexOf("UserDetailsService returned null") >= 0) {
                    //提示账号不存在
                    ExceptionCast.cast(AuthCode.AUTH_ACCOUNT_NOTEXISTS);
                }
            }
            //提示认证过程中申请令牌出错
            ExceptionCast.cast(AuthCode.AUTH_LOGIN_APPLYTOKEN_ERROR);
        }

        AuthToken authToken = new AuthToken();
        //设置身份令牌
        String access_token = (String) map.get("access_token");
        //设置刷新令牌
        String refresh_token = (String) map.get("refresh_token");
        //设置令牌标识
        String jti = (String) map.get("jti");
        authToken.setAccess_token(access_token);
        authToken.setRefresh_token(refresh_token);
        authToken.setJwt_token(jti);
        return authToken;
    }

    /**
     * 根据客户端id和客户端密码获取Basic的串
     * 请求头包含的客户端信息为 (客户端id:客户端密码)使用base64加密后的格式串
     * Basic 串信息
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String getHttpBasic(String clientId, String clientSecret) {
        //获取客户端格式字符串
        String httpBasic = clientId + ":" + clientSecret;
        //进行base64加密
        byte[] encode = Base64Utils.encode(httpBasic.getBytes());
        //固定格式输出
        return "Basic " + new String(encode);
    }

    /**
     * 保存令牌信息到redis数据库
     *
     * @param jti_token 令牌标识作为key
     * @param content   令牌全部内容
     * @param ttl       有效时间
     * @return
     */
    private boolean saveTokenToRedis(String jti_token, String content, long ttl) {
        //用户令牌内容作为key
        String name = "user_token:" + jti_token;
        //保存令牌信息到redis,设置key、内容和过期时间
        stringRedisTemplate.boundValueOps(name).set(content, ttl, TimeUnit.SECONDS);
        //获取过期时间
        Long expire = stringRedisTemplate.getExpire(name);
        return expire > 0;
    }



}
