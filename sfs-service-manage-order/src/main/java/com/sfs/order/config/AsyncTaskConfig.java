package com.sfs.order.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * 线程池调度任务
 *
 * @author yjw
 * @date 2020/6/23 23:54
 */
@EnableScheduling   //开启定时任务
@Configuration
@EnableAsync
public class AsyncTaskConfig implements AsyncConfigurer, SchedulingConfigurer {

    //线程池线程数量
    private int corePoolSize = 5;

    /**
     * 默认的定时任务线程池创建
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //初始化线程池
        scheduler.initialize();
        //指定线程池容量
        scheduler.setPoolSize(corePoolSize);
        //设置线程名称前缀
        scheduler.setThreadNamePrefix("sfs-task-scheduler-thread");
        //线程池关闭时等待所有任务关闭
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    /**
     * 自定义的定时任务线程池
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        //使用默认线程池
//        return taskScheduler();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(20);
        //配置最大线程数
        executor.setMaxPoolSize(50);
        //配置缓存队列大小
        executor.setQueueCapacity(100);
        //空闲队列存活时间
        executor.setKeepAliveSeconds(15);
        //设置线程名称前缀
        executor.setThreadNamePrefix("sfs-task-executor-thread");
        //线程池关闭时等待所有任务关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间
        executor.setAwaitTerminationSeconds(60);
        //初始化线程池
        executor.initialize();
        return executor;
    }

    /**
     * 设置默认线程池
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
    }

    /**
     * 处理异步执行任务的异常
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
