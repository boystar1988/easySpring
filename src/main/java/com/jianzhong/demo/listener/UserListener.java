package com.jianzhong.demo.listener;

import com.jianzhong.demo.constant.QueueConstant;
import com.jianzhong.demo.event.UserEvent;
import com.jianzhong.demo.event.UserRegisterEvent;
import com.jianzhong.demo.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserListener
{

    @Autowired
    QueueService queueService;

    @EventListener
    public void userEvent(UserEvent event)
    {
//        queueService.push(QueueConstant.DIRECT_QUEUE_USER, event.getData());
    }

    @EventListener
    public void userRegisterEvent(UserRegisterEvent event)
    {
//        queueService.push(QueueConstant.DIRECT_QUEUE_ORDER,event.getData());
    }
}