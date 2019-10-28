package com.jianzhong.demo.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.jianzhong.demo")
@EnableAsync
public class AsyncTaskConfig implements AsyncConfigurer {

    // ThredPoolTaskExcutor的处理流程
    // 当池子大小小于corePoolSize，就新建线程，并处理请求
    // 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
    // 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
    // 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁

    @Value(value = "${spring.task.execution.pool.core-size}")
    private Integer corePoolSize;
    @Value(value = "${spring.task.execution.pool.max-size}")
    private Integer maxPoolSize;
    @Value(value = "${spring.task.execution.pool.queue-capacity}")
    private Integer queueCapacity;
    @Value(value = "${spring.task.execution.pool.keep-alive}")
    private Integer keepAliveSeconds;


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);         // 最小线程数
        taskExecutor.setMaxPoolSize(maxPoolSize);           // 最大线程数
        taskExecutor.setQueueCapacity(queueCapacity);       // 等待队列
        taskExecutor.setKeepAliveSeconds(keepAliveSeconds); // 等待时间
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}