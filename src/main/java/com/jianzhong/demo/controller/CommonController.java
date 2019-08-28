package com.jianzhong.demo.controller;

import com.jianzhong.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@SuppressWarnings("unchecked")
public class CommonController
{
    @Autowired
    Result result;

    public Result asJson(Integer code,String message,Object data)
    {
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public Result success(Object data, String message)
    {
        result.setCode(200);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public Result error(String message,Integer code)
    {
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }
}