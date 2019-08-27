package com.jianzhong.demo.service;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{

    @Autowired
    UserMapper userMapper;

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