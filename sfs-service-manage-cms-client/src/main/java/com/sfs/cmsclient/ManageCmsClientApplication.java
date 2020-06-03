package com.sfs.cmsclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * cms系统消费者工程启动类
 * @author yjw
 * @date 2020/3/5 23:42
 */
@SpringBootApplication
@EntityScan("com.sfs.framework.domain.cms")                //为该服务层工程扫描model层的实体类
@ComponentScan(basePackages = {"com.sfs.framework"})       //扫描common工程下的类
@ComponentScan(basePackages = {"com.sfs.cmsclient"})      //扫描本项目下的所有类
public class ManageCmsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class, args);
    }

}
