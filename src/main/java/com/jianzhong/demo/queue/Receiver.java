package com.jianzhong.demo.queue;

import com.jianzhong.demo.config.QueueConfig;
import com.jianzhong.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE1)
    public void receiveTopic1(User user) {
        log.info("【receiveFanout1监听到消息】" + user);
    }

    @RabbitListener(queues = QueueConfig.FANOUT_QUEUE2)
    public void receiveTopic2(User user) {
        log.info("【receiveFanout2监听到消息】" + user);
    }
}