package com.jianzhong.demo.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Data
@Slf4j
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 事件数据
     */
    private Object data;

    public UserRegisterEvent(Object source, Object data)
    {
        super(source);
        this.data = data;
    }
}