package com.sfs.mediaprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author Administrator
 * @version 1.0
 * @create 2018-07-12 8:57
 **/
@SpringBootApplication
@EntityScan("com.sfs.framework.domain.media")//扫描实体类
@ComponentScan(basePackages={"com.sfs.api"})//扫描接口
@ComponentScan(basePackages={"com.sfs.mediaprocess"})//扫描本项目下的所有类
@ComponentScan(basePackages={"com.sfs.framework"})//扫描common下的所有类
public class ManageMediaProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageMediaProcessorApplication.class, args);
    }
}
