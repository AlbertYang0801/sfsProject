package com.sfs.auth;

import com.sfs.framework.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 认证服务启动类
 * @author yjw
 */
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("com.sfs.framework.domain.ucenter")//扫描实体类
@ComponentScan(basePackages={"com.sfs.api"})//扫描接口
@ComponentScan(basePackages={"com.sfs.framework"})//扫描common下的所有类
@SpringBootApplication
public class UcenterAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterAuthApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

//    /**
//     * 将feign拦截器注入到容器中，将请求头中的jwt令牌添加到restTemplate的请求头中
//     *
//     * @return
//     */
//    @Bean
//    public FeignClientInterceptor feignClientInterceptor(){
//        return new FeignClientInterceptor();
//    }

}
