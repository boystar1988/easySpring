package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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

    User loadUserByUsername(String username);

    List<User> selectByPrimaryKeys(List<Long> uids);

    /**
     * 联表查询
     * @param uid 用户ID
     * @return User
     */
    @Select("SELECT uid,username,create_time,update_time,is_del,is_enabled,roles FROM sp_user WHERE uid = #{uid}")
    @Results({
        @Result(property = "uid", column = "uid"),
        @Result(property = "create_time", column = "create_time"),
        @Result(property = "update_time", column = "update_time"),
        @Result(property = "is_enabled", column = "is_enabled"),
        @Result(property = "is_del", column = "is_del"),
        @Result(property = "userMails", column = "uid", many = @Many(select = "com.jianzhong.demo.repository.UserMailMapper.getUserMailByUserId"))
    })
    User getUserWithUserMail(Long uid);
}