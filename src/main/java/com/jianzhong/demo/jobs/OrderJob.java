package com.jianzhong.demo.jobs;

import com.jianzhong.demo.constant.QueueConstant;
import com.jianzhong.demo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderJob
{

    @Autowired
    MailService mailService;

    @RabbitListener(queues = QueueConstant.DIRECT_QUEUE_ORDER)
    public void process(Object data)
    {
        log.info("订单队列添加成功：" + data);
        mailService.sendHtml("814411929@qq.com","建众帮","<a href=\"http://www.jianzhongbang.com\">感谢你注册建众帮</a>");
    }
}