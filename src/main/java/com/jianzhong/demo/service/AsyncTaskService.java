package com.jianzhong.demo.service;

import java.util.Random;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncTaskService {

    Random random = new Random();// 默认构造方法

    @Async
    public void executeAsyncTask(String msg) {
        log.info(Thread.currentThread().getName()+"开启新线程执行" + msg);
    }

    /**
     * 异常调用返回Future
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
        log.info("input is " + i);
        Thread.sleep(1000 * random.nextInt(i));
        return new AsyncResult<String>("success:" + i);// Future接收返回值，这里是String类型，可以指明其他类型
    }
}