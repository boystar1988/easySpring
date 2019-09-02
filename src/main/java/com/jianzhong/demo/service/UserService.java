package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.event.UserRegisterEvent;
import com.jianzhong.demo.repository.UserMapper;
import com.jianzhong.demo.security.AuthPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.catalina.Role;

import javax.management.relation.RoleList;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserService implements Serializable, UserDetailsService
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

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        List<Role> roles = new ArrayList<>();
        user.setRoles(roles);
        return user;
    }

}