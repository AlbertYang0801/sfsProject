package com.sfs.managecourse;

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
 **/
@SpringBootApplication      //标注该工程为一个SpringBoot应用
@EnableDiscoveryClient      //标注该工程为eureka的一个客户端
@EnableFeignClients         //扫描FeignClient对象
@EntityScan("com.sfs.framework.domain.course")//扫描实体类
@ComponentScan(basePackages = {"com.sfs.api"})//扫描接口
@ComponentScan(basePackages = {"com.sfs.managecourse"})
@ComponentScan(basePackages = {"com.sfs.framework"})//扫描common下的所有类
public class ManageCourseApplication {
    public static void main(String[] args)  {
        SpringApplication.run(ManageCourseApplication.class, args);
    }

    /**
     * 为该工程注入RestTemplate远程调用方法
     * 使用Okhttp
     * @LoadBanlanced注解使RestTemplate在进行请求的时候具有负载均衡的能力
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
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
