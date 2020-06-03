package com.sfs.cmsclient.mq;

import com.alibaba.fastjson.JSON;
import com.sfs.cmsclient.dao.CmsPageRepository;
import com.sfs.cmsclient.service.PageService;
import com.sfs.framework.domain.cms.CmsPage;
import com.sfs.framework.domain.cms.response.CmsCode;
import com.sfs.framework.exception.ExceptionCast;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Cms系统RabbitMQ的消费者
 * 监听交换机的指定队列，监听到之后的消费行为
 * @author  yjw
 * on 2019/11/1
 */
@Component
public class ConsumerPostPage {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private PageService pageService;

    /**
     * 监听队列，队列名为配置文件配置的队列名
     * @param msg
     */
    @RabbitListener(queues = "${sfs.mq.queue_01}")
    public void postPageCms(String msg){
        postPage(msg);
    }

    /**
     * 监听队列，队列名为配置文件配置的队列名
     * @param msg
     */
    @RabbitListener(queues = "${sfs.mq.queue_02}")
    public void postPageCourse(String msg){
       postPage(msg);
    }

    private void postPage(String msg){
        Map map = JSON.parseObject(msg);
        //从消息队列中获取pageId
        String pageId = (String) map.get("pageId");
        Optional<CmsPage> cmsPage = cmsPageRepository.findById(pageId);
        if(!cmsPage.isPresent()){
            //调用自定义异常抛出类 提示发布页面为空
            ExceptionCast.cast(CmsCode.CMS_POSTPAGE_PAGEISNULL);
        }
        //根据pageId发布文件到指定物理路径
        pageService.savePageToServerPath(pageId);
    }


}

//Cms客户端的消费者，监听指定队列，从队列中获取pageId
//根据pageId判断页面是否存在
//页面存在，根据pageId发布页面的html文件到指定物理路径
