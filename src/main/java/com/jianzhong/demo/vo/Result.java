package com.jianzhong.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
public class Result<T>
{
    @ApiModelProperty(value = "错误码")
    private int code;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "数据")
    private T data;

    public Result()
    {
    }

    public Result(int code,String msg)
    {
        this(code,msg,null);
    }

    public Result(int code,String msg,T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}