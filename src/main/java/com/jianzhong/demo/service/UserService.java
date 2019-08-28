package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.event.UserEvent;
import com.jianzhong.demo.event.UserRegisterEvent;
import com.jianzhong.demo.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserService implements Serializable
{

    @Autowired
    UserMapper userMapper;
    @Autowired
    private ApplicationContext publisher;

    public int deleteByPrimaryKey(Integer uid)
    {
        return userMapper.deleteByPrimaryKey(uid);
    }

    public int insert(User record)
    {
        return userMapper.insert(record);
    }

    public int insertSelective(User record)
    {
        return userMapper.insertSelective(record);
    }

    public User selectByPrimaryKey(Integer uid)
    {
        Map data = new HashMap();
        data.put("uid",uid);
        UserEvent event = new UserEvent(this, data);
        publisher.publishEvent(event);
        log.info("详情事件发布成功");
        return userMapper.selectByPrimaryKey(uid);
    }

    public List<User> select()
    {
        Map data = new HashMap();
        data.put("user","abc");
        UserRegisterEvent event = new UserRegisterEvent(this, data);
        publisher.publishEvent(event);
        log.info("列表事件发布成功");
        return userMapper.select();
    }

    public int updateByPrimaryKeySelective(User record)
    {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(User record)
    {
        return userMapper.updateByPrimaryKey(record);
    }

}