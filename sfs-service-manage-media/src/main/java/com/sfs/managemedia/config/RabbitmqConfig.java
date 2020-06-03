package com.sfs.managemedia.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-07-12 9:04
 **/
@Configuration
public class RabbitmqConfig {

    /**
     * 视频处理交换机名称
     */
    public static final String EX_MEDIA_PROCESSTASK = "ex_media_processor";

    /**
     * 视频处理的队列名称
     */
    @Value("${sfs-service-manage-media.mq.queue-media-video-processor}")
    public String queue_media_video_processtask;

    /**
     * 视频处理路由
     */
    @Value("${sfs-service-manage-media.mq.routingkey-media-video}")
    public String routingkey_media_video;

    /**
     * 交换机配置
     *
     * @return the exchange
     */
    @Bean(EX_MEDIA_PROCESSTASK)
    public Exchange EX_MEDIA_VIDEOTASK() {
        return ExchangeBuilder.directExchange(EX_MEDIA_PROCESSTASK).durable(true).build();
    }

    /**
     * 声明视频处理的队列
     *
     * @return
     */
    @Bean("queue_media_video_processtask")
    public Queue QUEUE_PROCESSTASK() {
        Queue queue = new Queue(queue_media_video_processtask, true, false, true);
        return queue;
    }

    /**
     * 绑定队列到交换机 .
     *
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    public Binding binding_queue_media_processtask(@Qualifier("queue_media_video_processtask") Queue queue, @Qualifier(EX_MEDIA_PROCESSTASK) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey_media_video).noargs();
    }

}
