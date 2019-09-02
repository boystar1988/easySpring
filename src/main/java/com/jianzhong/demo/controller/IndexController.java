package com.jianzhong.demo.controller;

import com.jianzhong.demo.service.AuthService;
import com.jianzhong.demo.utils.RedisUtil;
import com.jianzhong.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController extends CommonController{

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AuthService authService;

    @RequestMapping("/")
    @ResponseBody
    public ResultVo Index(HttpServletRequest request)
    {
        Map data = new HashMap();
        return this.success(data,"Hello "
                +authService.getUser().getUsername()
                +authService.getDevice()
                +authService.getVersion());
    }

    @RequestMapping("/redis")
    @ResponseBody
    public ResultVo redis()
    {
        int timeInt = (int)System.currentTimeMillis();
        String time = Integer.toString(timeInt);
        redisUtil.set("SpringBoot",time,0);
        String data = redisUtil.get("SpringBoot",0);
        return this.success(data,"success");
    }

}