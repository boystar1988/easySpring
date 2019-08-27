package com.jianzhong.demo.controller;

import com.jianzhong.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map Index()
    {
        Map result = new HashMap();
        Map data = new HashMap();
        result.put("code",200);
        result.put("msg","Spring Boot");
        result.put("data",data);
        return result;
    }

    @RequestMapping("/redis")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map redis()
    {
        Map result = new HashMap();
        int timeInt = (int)System.currentTimeMillis();
        String time = Integer.toString(timeInt);
        redisUtil.set("SpringBoot",time,0);
        String data = redisUtil.get("SpringBoot",0);
        result.put("code",200);
        result.put("msg","Spring Boot");
        result.put("data",data);
        return result;
    }

}