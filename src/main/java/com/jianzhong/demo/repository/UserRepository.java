package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>
{
    @Query("select u from User u where u.username=:username")
    public User findUserByUsername(@Param("username") String username);

    @Query("select u from User u where u.uid=:uid")
    public User findUserByUid(Long uid);
}