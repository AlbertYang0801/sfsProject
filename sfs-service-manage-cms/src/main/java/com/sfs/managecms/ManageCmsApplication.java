package com.sfs.managecms;

import com.sfs.framework.interceptor.FeignClientInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Cms服务层工程启动类
 * @author yjw
 * @create 2019/8/15 17:40
 */
@SpringBootApplication
@EnableDiscoveryClient              //表示该工程为eureka的一个客户端
@EntityScan("com.sfs.framework.domain.cms")                //为该服务层工程扫描model层的实体类
@ComponentScan(basePackages = {"com.sfs.api"})             //扫描api工程的接口
@ComponentScan(basePackages = {"com.sfs.managecms"})      //扫描本项目下的所有类
@ComponentScan(basePackages = {"com.sfs.framework"})       //扫描common工程下的类
public class ManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }

    /**
     * 将请求模板类RestTemplate注入到容器中
     */
    @Bean
    public RestTemplate restTemplate(){
        //创建请求模板类，使用OkHttpClient完成http请求
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
