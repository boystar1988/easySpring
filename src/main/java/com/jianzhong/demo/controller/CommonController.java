package com.jianzhong.demo.controller;

import com.jianzhong.demo.constant.ResultConstant;
import com.jianzhong.demo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unchecked")
public class CommonController
{
    @Autowired
    ResultVo result;

    public ResultVo asJson(Integer code, String message, Object data)
    {
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public ResultVo success(Object data, String message)
    {
        result.setCode(ResultConstant.CODE_SUCCESS);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

    public ResultVo error(String message, Integer code)
    {
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);
        return result;
    }
}