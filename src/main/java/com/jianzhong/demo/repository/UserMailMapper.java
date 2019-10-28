package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.UserMail;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMailMapper
{
    @Select("SELECT * FROM `sp_user_mail` WHERE uid = #{uid}")
    @Results({
        @Result(property = "user_mail_id",  column = "user_mail_id"),
        @Result(property = "mail",  column = "mail"),
        @Result(property = "create_time",  column = "create_time"),
        @Result(property = "update_time",  column = "update_time"),
        @Result(property = "is_del",  column = "is_del"),
    })
    List<UserMail> getUserMailByUserId(Long uid);
}