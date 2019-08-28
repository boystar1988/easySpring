package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> select();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}