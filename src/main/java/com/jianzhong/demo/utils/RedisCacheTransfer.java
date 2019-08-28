package com.jianzhong.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheTransfer
{
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate)
    {
        RedisCacheUtil.setRedisTemplate(redisTemplate);
    }

}