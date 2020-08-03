package com.sfs.order.mq;

import com.sfs.framework.domain.task.SfsTask;
import com.sfs.order.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 选课信息定时任务配置类
 * @author yjw
 * @date 2020/6/23 21:23
 */
@Component
public class ChooseCourseTask {

    private static Logger logger = LoggerFactory.getLogger(ChooseCourseTask.class);

    @Autowired
    private TaskService taskService;

    /**
     * 在当前任务完成后10秒执行下一个任务
     * 任务内容：1：扫描消息表，2：向mq发消息
     */
    @Scheduled(fixedDelay = 10000)
    public void sendChooseCourseTask(){
        //获取当前时间的前1分钟
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(GregorianCalendar.MINUTE,-1);
        Date time = calendar.getTime();
        //1.获取当前时间前1分钟之前的100条任务
        List<SfsTask> list = taskService.findSfsTask(time, 100);
        for (SfsTask sfsTask : list) {
            //2.向mq发消息，将添加选课的信息发送给mq
            taskService.publish(sfsTask,sfsTask.getMqExchange(),sfsTask.getMqRoutingkey());
            logger.info("发送选课消息到mq，任务id:"+sfsTask.getId());
        }
    }

}

