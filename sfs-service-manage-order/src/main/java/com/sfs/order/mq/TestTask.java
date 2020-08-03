package com.sfs.order.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 测试SpringTask
 * 1.测试cron表达式
 * 2.测试串行任务
 * 3.测试并行任务
 * @author yjw
 * @date 2020/6/24 23:42
 */
//@EnableAsync
//@Component
public class TestTask {

    private static Logger logger = LoggerFactory.getLogger(TestTask.class);

    /**
     * 测试基本的定时任务
     */
//    @Async
    @Scheduled(cron = "0/5 * * * * *")  //每隔5秒执行一次任务
//    @Scheduled(fixedRate = 5000)    //在任务开始5秒后执行下一次任务
//    @Scheduled(fixedDelay = 5000)   //在任务结束5秒后执行下一次任务
    public void testTask(){
        logger.info("定时任务执行开始");
        try {
            //模拟执行时长为5秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("定时任务执行结束");
    }

    /**
     * 第二个定时任务，测试串行
     */
//    @Async
    @Scheduled(cron = "0/5 * * * * *")
//    @Scheduled(initialDelay = 1000,fixedRate = 5000)    //在任务开始5秒后执行下一次任务
    public void testSerial(){
        logger.info(Thread.currentThread().getName()+" 测试并行，第二个定时任务开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("测试并行，第二个定时任务结束");
    }

    //    @Scheduled(cron = "0 50 23 * * 1-5")      //23点40分执行
//    @Scheduled(cron = "0 0 12 * * * ")      //每天的12点
    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void testCron(){
        logger.info(Thread.currentThread().getName()+" 测试自定义线程池，第三个定时任务开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("测试自定义线程池，第三个定时任务结束");
    }


}

/**
 * cron表达式：
 * cron=" * * * * * *"
 * 第一个:秒
 * 第二个:分
 * 第三个:小时
 * 第四个:每月的哪一天
 * 第五个:月份
 * 第六个:周几
 */