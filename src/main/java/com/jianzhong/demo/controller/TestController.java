package com.jianzhong.demo.controller;

import com.jianzhong.demo.domain.SpUser;
import com.jianzhong.demo.repository.SpUserRepository;
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
    private SpUserRepository userRepository;

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
        SpUser res = userRepository.findUserByName("b");
        return this.success(res,"success");
    }

}