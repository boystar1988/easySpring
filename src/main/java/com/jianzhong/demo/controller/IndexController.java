package com.jianzhong.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

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

}