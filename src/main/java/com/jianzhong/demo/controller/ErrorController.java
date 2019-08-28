package com.jianzhong.demo.controller;

import com.jianzhong.demo.constant.ResultConstant;
import com.jianzhong.demo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@SuppressWarnings("unchecked")
public class ErrorController
{
    @Autowired
    private ResultVo result;

    @RequestMapping("/500")
    @ResponseBody
    public ResultVo ServerError(String message)
    {
        log.error(message);
        result.setCode(ResultConstant.CODE_ERROR);
        result.setMsg("服务器繁忙，请稍后再试!");
        result.setData(null);
        return result;
    }

    @RequestMapping("/401")
    @ResponseBody
    public ResultVo NoAuth()
    {
        result.setCode(ResultConstant.CODE_NOAUTH);
        result.setMsg("未经授权");
        result.setData(null);
        return result;
    }

    @RequestMapping("/403")
    @ResponseBody
    public ResultVo NoPermission()
    {
        result.setCode(ResultConstant.CODE_NOPERMISSION);
        result.setMsg("你没有权限访问该页面");
        result.setData(null);
        return result;
    }

    @RequestMapping("/404")
    @ResponseBody
    public ResultVo NotFound()
    {
        result.setCode(ResultConstant.CODE_NOTFOUND);
        result.setMsg("页面未找到");
        result.setData(null);
        return result;
    }


}