package com.jianzhong.demo.jobs;

import com.jianzhong.demo.constant.QueueConstant;
import com.jianzhong.demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserJob
{

    @Autowired
    MailService mailService;

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE_USER)
    public void process(Object data)
    {
        log.info("用户队列添加成功：" + data);
        mailService.send("814411929@qq.com","建众帮","感谢你注册建众帮");
    }

}