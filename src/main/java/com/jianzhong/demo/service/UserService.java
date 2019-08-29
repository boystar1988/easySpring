package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
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

    public int deleteByPrimaryKey(Long uid)
    {
        return userMapper.deleteByPrimaryKey(uid);
    }

    public int insert(User record)
    {
        return userMapper.insert(record);
    }

    public int insertSelective(User record)
    {
        int uid = userMapper.insertSelective(record);
        Map data = new HashMap();
        data.put("uid",record);
        UserRegisterEvent event = new UserRegisterEvent(this, data);
        publisher.publishEvent(event);
        return uid;
    }

    public User selectByPrimaryKey(Long uid)
    {
        return userMapper.selectByPrimaryKey(uid);
    }

    public List<User> select()
    {
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