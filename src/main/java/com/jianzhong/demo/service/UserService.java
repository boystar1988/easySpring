package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.event.UserRegisterEvent;
import com.jianzhong.demo.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.*;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserService implements Serializable, UserDetailsService
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    private ApplicationContext publisher;

    public int deleteUserByUid(Long uid)
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

    public User findUserByUid(Long uid)
    {
        return userMapper.selectByPrimaryKey(uid);
    }

    public List<User> findAllUser()
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

    public User getUserWithUserMail(Long uid)
    {
        return userMapper.getUserWithUserMail(uid);
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        return user;
    }
}