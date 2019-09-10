package com.jianzhong.demo.repository;

import com.jianzhong.demo.domain.SpUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpUserRepository extends CrudRepository<SpUser ,Long>
{
    @Query("select u from SpUser u where u.username=:username")
    public SpUser findUserByName(@Param("username") String username);

    public SpUser findUserByUsername(String username);
}