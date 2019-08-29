package com.jianzhong.demo.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class User implements Serializable
{
    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否删除")
    private Integer is_del;

    @ApiModelProperty(value = "创建时间")
    private Integer create_time;

    @ApiModelProperty(value = "更新时间")
    private Integer update_time;

    @ApiModelProperty(value = "是否VIP")
    public Integer is_vip;

    public Long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }

    public Integer getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Integer update_time) {
        this.update_time = update_time;
    }
}