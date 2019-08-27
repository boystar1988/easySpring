package com.jianzhong.demo.listener;

import com.jianzhong.demo.config.QueueConfig;
import com.jianzhong.demo.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class QueueListener {

    @Autowired
    QueueService queueService;

    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE1)
    public void receiveTopic1(Object data) {
        log.info("【队列1监听到消息】" + data);
    }

    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE2)
    public void receiveTopic2(Object data) {
        log.info("【队列2监听到消息】" + data);
    }
}