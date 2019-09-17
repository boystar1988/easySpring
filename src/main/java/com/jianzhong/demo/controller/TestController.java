package com.jianzhong.demo.controller;

import com.jianzhong.demo.domain.User;
import com.jianzhong.demo.repository.UserRepository;
import com.jianzhong.demo.service.AsyncTaskService;
import com.jianzhong.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController extends CommonController{

    @Autowired
    private AsyncTaskService asyncTaskService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    @ResponseBody
    public ResultVo Index(HttpServletRequest request)
    {
        return this.success(null,"测试页面");
    }

    @RequestMapping("/async-task")
    @ResponseBody
    public ResultVo asyncTask(){
        asyncTaskService.executeAsyncTask("hello1");
        asyncTaskService.executeAsyncTask("hello2");
        asyncTaskService.executeAsyncTask("hello3");
        asyncTaskService.executeAsyncTask("hello4");
        asyncTaskService.executeAsyncTask("hello5");
        return this.success(null,"多线程创建成功");
    }

    @RequestMapping("/user")
    @ResponseBody
    public ResultVo getUser(){
        Long uid = Long.valueOf(2);
        User res = userRepository.findUserByUid(uid);
        return this.success(res,"success");
    }

}