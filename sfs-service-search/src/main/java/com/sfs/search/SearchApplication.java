package com.sfs.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 搜索工程的启动类
 * @author Administrator
 * @version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient      //标注该工程为eureka的一个客户端
@EntityScan("com.sfs.framework.domain.search")//扫描实体类
@ComponentScan(basePackages={"com.sfs.api"})//扫描接口
@ComponentScan(basePackages={"com.sfs.search"})//扫描本项目下的所有类
@ComponentScan(basePackages={"com.sfs.framework"})//扫描common下的所有类
public class SearchApplication {

    public static void main(String[] args){
        SpringApplication.run(SearchApplication.class, args);
    }

}
