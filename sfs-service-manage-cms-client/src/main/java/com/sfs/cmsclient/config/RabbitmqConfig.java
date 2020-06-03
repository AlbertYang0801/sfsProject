package com.sfs.cmsclient.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CMS客户端的RabbitMQ消费端配置类
 * 配置队列、交换机、路由key、绑定队列到交换机
 * @author  yjw
 * on 2019/11/1
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 交换机名称(唯一的，和生产者交换机名称一致)
     */
    public static final String EX_ROUTING_CMS_POSTPAGE = "ex_routing_cms_postpage";

    /**
     * cms队列bean名称
     */
    public static final String QUEUE_CMS_POSTPAGE = "queue_cms_postpage";

    /**
     * 课程详情队列bean名称
     */
    public static final String QUEUE_COURSE_POSTPAGE = "queue_course_postpage";

    /**
     * cms队列名称
     */
    @Value("${sfs.mq.queue_01}")
    public String queue_cms_postpage_name;

    /**
     * cms路由key 即门户站点Id
     */
    @Value("${sfs.mq.routingKey_01}")
    public String routingKeyCms;

    /**
     * course队列名称
     */
    @Value("${sfs.mq.queue_02}")
    public String queue_course_postpage_name;

    /**
     * course路由key 即课程详情站点Id
     */
    @Value("${sfs.mq.routingKey_02}")
    public String routingKeyCourse;

    /**
     * 声明交换机
     * 使用direct类型(路由工作模式)
     * @return
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EXCHANGE_TOPICS_INFORM(){
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }

    /**
     * 声明cms队列(将配置文件中的队列名称注入到容器中)
     * @return
     */
    @Bean(QUEUE_CMS_POSTPAGE)
    public Queue QUEUE_CMS_POSTPAGE(){
        return new Queue(queue_cms_postpage_name);
    }

    /**
     * 声明course队列(将配置文件中的队列名称注入到容器中)
     * @return
     */
    @Bean(QUEUE_COURSE_POSTPAGE)
    public Queue QUEUE_COURSE_POSTPAGE(){
        return new Queue(queue_course_postpage_name);
    }

    /**
     * 绑定cms的队列到交换机，并设置门户站点作为路由key
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding BINDING_CMSQUEUE_INFORM(@Qualifier(QUEUE_CMS_POSTPAGE)Queue queue,
                                        @Qualifier(EX_ROUTING_CMS_POSTPAGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyCms).noargs();
    }

    /**
     * 绑定cms的队列到交换机，并设置门户站点作为路由key
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding BINDING_COURSEQUEUE_INFORM(@Qualifier(QUEUE_COURSE_POSTPAGE)Queue queue,
                                        @Qualifier(EX_ROUTING_CMS_POSTPAGE)Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyCourse).noargs();
    }


}
