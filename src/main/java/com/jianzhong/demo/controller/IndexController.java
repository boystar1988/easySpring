package com.jianzhong.demo.controller;

import com.jianzhong.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController extends CommonController{

    @Value(value = "${spring.application.name}")
    private String appName;

    @RequestMapping("/")
    @ResponseBody
    public ResultVo Index(HttpServletRequest request)
    {
        return this.success(null,"Hello " + appName);
    }

}