package com.jianzhong.demo.listener;

import com.jianzhong.demo.event.UserEvent;
import com.jianzhong.demo.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

@Slf4j
public class UserListener implements ApplicationListener<UserEvent> {

    @Autowired
    QueueService queueService;

    @Override
    @EventListener
    public void onApplicationEvent(UserEvent event) {
        log.info("监听到UserEvent事件: " + event.getData());
        queueService.send(event.getData());
    }
}