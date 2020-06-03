package com.sfs.auth;

import com.sfs.framework.client.SfsServiceList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * 密码授权模式测试
 * @author yjw
 * @date 2020/5/5 19:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClient {


    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 密码模式申请jwt令牌
     */
    @Test
    public void getJwt() {

        //从eureka中获取认证服务的IP地址和端口
        ServiceInstance serviceInstance = loadBalancerClient.choose(SfsServiceList.SFS_SERVICE_UCENTER_AUTH);

        URI uri = serviceInstance.getUri();
        //设置令牌申请的地址（http://localhost:40400/auth/oauth/token）
        String authUrl = uri + "/auth/oauth/token";

        //设置headers内容
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic("XcWebApp", "XcWebApp");
        headers.add("Authorization", httpBasic);

        //设置body内容
        LinkedMultiValueMap<String, String> bodys= new LinkedMultiValueMap<>();
        bodys.add("grant_type", "password");
        bodys.add("username", "itcast");
        bodys.add("password", "123");


        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(bodys, headers);

        //设置在请求时，400和401错误不报异常，正常返回结果
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //400和401请求正常放行
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        //使用restTemplate远程申请调用令牌
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        Map body = exchange.getBody();
        System.out.println(body);

    }

    /**
     * 根据客户端id和客户端密码获取Basic的串
     *
     * @param clientId
     * @param clientPwd
     * @return
     */
    private String getHttpBasic(String clientId, String clientPwd) {
        String httpBasic = clientId + ":" + clientPwd;
        byte[] encode = Base64Utils.encode(httpBasic.getBytes());
        return "Basic " + new String(encode);
    }

    /**
     * 测试加密方式
     * 使用bcrypt进行加密，随机加盐，保证密码一致加密后结果也不一致
     */
    @Test
    public void testPasswordEncoder(){
        String password = "111111";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for(int i=0;i<5;i++){
            //使用bcrypt进行自动加盐加密
            String encode = bCryptPasswordEncoder.encode(password);
            System.out.println(encode);
            boolean matches = bCryptPasswordEncoder.matches(password, encode);
            System.out.println(matches);
        }
    }



}


