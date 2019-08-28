package com.jianzhong.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@SuppressWarnings("unchecked")
public class CommonController
{
    public Map asJson(Integer code,String message,Object data)
    {
        Map result = new HashMap();
        result.put("code",code);
        result.put("msg",message);
        result.put("data",data);
        return result;
    }

    public Map success(Object data,String message)
    {
        Map result = new HashMap();
        result.put("code",200);
        result.put("msg",message);
        result.put("data",data);
        return result;
    }

    public Map error(String message,Integer code)
    {
        Map result = new HashMap();
        result.put("code",code);
        result.put("msg",message);
        result.put("data",null);
        return result;
    }
}