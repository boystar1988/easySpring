package com.jianzhong.demo.listener;

import com.jianzhong.demo.event.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

@Slf4j
public class UserListener implements ApplicationListener<UserEvent> {

    @Override
    @EventListener
    public void onApplicationEvent(UserEvent event) {
        log.info("监听到UserEvent事件: " + event.getData());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}