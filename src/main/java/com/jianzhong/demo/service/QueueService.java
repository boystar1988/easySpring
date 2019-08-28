package com.jianzhong.demo.service;

import com.jianzhong.demo.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueueService
{
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void push(String queueName,Object data)
    {
        log.info("添加到队列："+data.toString());
        this.rabbitTemplate.convertAndSend(QueueConstant.DIRECT_EXCHANGE, queueName, data);
    }
}