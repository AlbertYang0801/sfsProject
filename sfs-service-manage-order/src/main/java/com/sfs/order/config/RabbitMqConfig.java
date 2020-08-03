package com.sfs.order.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 选课相关的mq配置
 * @author yjw
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 添加选课任务交换机
     */
    public static final String EX_LEARNING_ADDCHOOSECOURSE = "ex_learning_addchoosecourse";

    /**
     * 添加选课消息队列
     */
    private static final String SFS_LEARNING_ADDCHOOSECOURSE = "sfs_learning_addchoosecourse";

    /**
     * 完成添加选课消息队列
     */
    public static final String SFS_LEARNING_FINISHADDCHOOSECOURSE = "sfs_learning_finishaddchoosecourse";

    /**
     * 添加选课路由key
     */
    public static final String SFS_LEARNING_ADDCHOOSECOURSE_KEY = "addchoosecourse";

    /**
     * 完成添加选课路由key
     */
    public static final String SFS_LEARNING_FINISHADDCHOOSECOURSE_KEY = "finishaddchoosecourse";


    /**
     * 交换机配置
     * @return the exchange
     */
    @Bean(EX_LEARNING_ADDCHOOSECOURSE)
    public Exchange EX_DECLARE() {
        return ExchangeBuilder.directExchange(EX_LEARNING_ADDCHOOSECOURSE).durable(true).build();
    }

    /**
     * 声明添加选课队列
     * @return
     */
    @Bean("sfs_learning_addchoosecourse")
    public Queue QUEUE_ADD(){
        Queue queue = new Queue(SFS_LEARNING_ADDCHOOSECOURSE,true,false,true);
        return queue;
    }

    /**
     * 声明完成添加选课队列
     * @return
     */
    @Bean("sfs_learning_finishaddchoosecourse")
    public Queue QUEUE_FINISH() {
        Queue queue = new Queue(SFS_LEARNING_FINISHADDCHOOSECOURSE,true,false,true);
        return queue;
    }

    /**
     * 绑定添加选课队列到交换机，指定添加选课的路由key
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding_queue_add(@Qualifier("sfs_learning_addchoosecourse") Queue queue,
                                     @Qualifier(EX_LEARNING_ADDCHOOSECOURSE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(SFS_LEARNING_ADDCHOOSECOURSE_KEY).noargs();
    }

    /**
     * 绑定完成添加选课队列到交换机，指定完成添加选课的路由key
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding binding_queue_finish(@Qualifier("sfs_learning_finishaddchoosecourse") Queue queue,
                                        @Qualifier(EX_LEARNING_ADDCHOOSECOURSE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SFS_LEARNING_FINISHADDCHOOSECOURSE_KEY).noargs();
    }

}
