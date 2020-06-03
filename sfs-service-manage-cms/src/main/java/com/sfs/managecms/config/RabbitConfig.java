package com.sfs.managecms.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ生产方配置类
 * 路由工作模式
 * @author  yjw
 * on 2019/11/6
 */
@Configuration
public class RabbitConfig {

    /**
     * 交换机名称
     */
    public static final String EX_ROUTING_CMS_POSTPAGE = "ex_routing_cms_postpage";

    /**
     * 声明direct模式的交换机
     * @return
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EX_ROUTING_CMS_POSTPAGE(){
        //durable=true 支持交换机持久化，mq重启后交换机还存在
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }

}
