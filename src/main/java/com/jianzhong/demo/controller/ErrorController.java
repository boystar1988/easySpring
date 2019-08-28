package com.jianzhong.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@SuppressWarnings("unchecked")
public class ErrorController {

    @RequestMapping("/500")
    @ResponseBody
    public Map ServerError(String message)
    {
        Map result = new HashMap();
        result.put("code",500);
        result.put("msg","服务器繁忙，请稍后再试!");
        result.put("data",null);
        log.error(message);
        return result;
    }

    @RequestMapping("/401")
    @ResponseBody
    public Map NoAuth()
    {
        Map result = new HashMap();
        result.put("code",401);
        result.put("msg","未经授权");
        result.put("data",null);
        return result;
    }

    @RequestMapping("/403")
    @ResponseBody
    public Map NoPermission()
    {
        Map result = new HashMap();
        result.put("code",403);
        result.put("msg","你没有权限访问该页面");
        result.put("data",null);
        return result;
    }

    @RequestMapping("/404")
    @ResponseBody
    public Map NotFound()
    {
        Map result = new HashMap();
        result.put("code",404);
        result.put("msg","页面未找到");
        result.put("data",null);
        return result;
    }


}