package com.sfs.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @version 1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication//扫描所在包及子包的bean，注入到ioc中
@EntityScan("com.sfs.framework.domain.filesystem")//扫描实体类
@ComponentScan(basePackages={"com.sfs.api"})//扫描接口
@ComponentScan(basePackages={"com.sfs.framework"})//扫描framework中通用类
@ComponentScan(basePackages={"com.sfs.filesystem"})//扫描本项目下的所有类
public class FileSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class,args);
    }


}
