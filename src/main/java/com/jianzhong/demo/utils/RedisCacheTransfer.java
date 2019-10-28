package com.jianzhong.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheTransfer
{
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate)
    {
        RedisCacheUtil.setRedisTemplate(redisTemplate);
    }
}