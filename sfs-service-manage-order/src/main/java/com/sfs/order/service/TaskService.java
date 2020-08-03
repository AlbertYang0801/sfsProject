package com.sfs.order.service;

import com.sfs.framework.domain.task.SfsTask;
import com.sfs.order.dao.SfsTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 选课任务表操作
 *
 * @author yjw
 * @date 2020/6/24 23:34
 */
@Service
public class TaskService {

    @Autowired
    private SfsTaskRepository sfsTaskRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 取出指定时间之前的n个任务
     *
     * @param updateTime
     * @param n
     * @return
     */
    public List<SfsTask> findSfsTask(Date updateTime, int n) {
        //设置分页参数，取出前n个任务
        Pageable pageable = new PageRequest(0, n);
        //查出指定时间前n个任务
        Page<SfsTask> page = sfsTaskRepository.findByUpdateTimeBefore(pageable, updateTime);
        return page.getContent();
    }

    /**
     * 向mq发送添加选课的消息
     *  @param sfsTask    消息内容
     * @param ex    交换机
     * @param routingKey    路由key
     */
    @Transactional
    public void publish(SfsTask sfsTask, String ex, String routingKey) {
        Optional<SfsTask> optional = sfsTaskRepository.findById(sfsTask.getId());
        if (optional.isPresent()) {
            SfsTask data = optional.get();
            //向mq发消息，指定交换机和路由key，发送消息内容
            rabbitTemplate.convertAndSend(ex,routingKey,sfsTask);
            //更新任务时间为当前时间
            sfsTaskRepository.updateTaskTime(sfsTask.getId(),new Date());
        }
    }


}
