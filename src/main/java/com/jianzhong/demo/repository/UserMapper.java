package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper
{
    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long uid);

    List<User> select();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}