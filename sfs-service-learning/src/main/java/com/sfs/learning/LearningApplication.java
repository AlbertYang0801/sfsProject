package com.sfs.learning;

import com.sfs.framework.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-07-14 11:11
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(value = {"com.sfs.framework.domain.learning","com.sfs.framework.domain.task"})//扫描实体类
@ComponentScan(basePackages={"com.sfs.api"})//扫描接口
@ComponentScan(basePackages={"com.sfs.learning"})//扫描接口
@ComponentScan(basePackages={"com.sfs.framework"})//扫描common下的所有类
public class LearningApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LearningApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    /**
     * 将feign拦截器注入到容器中，将请求头中的jwt令牌添加到restTemplate的请求头中
     *
     * @return
     */
    @Bean
    public FeignClientInterceptor feignClientInterceptor(){
        return new FeignClientInterceptor();
    }

}