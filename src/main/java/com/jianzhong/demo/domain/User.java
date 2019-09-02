package com.jianzhong.demo.domain;

import io.swagger.annotations.ApiModelProperty;
//import org.apache.catalina.Role;
import org.apache.catalina.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails
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

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "系统角色")
    private List<Role> roles;

    @ApiModelProperty(value = "是否启用")
    private Integer is_enabled;

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

    public String getRole() {
        return role;
    }

    public void setRole(String roles) {
        this.role = role;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(Integer is_enabled) {
        this.is_enabled = is_enabled;
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    //账号过期
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号锁定
    public boolean isAccountNonLocked(){
        return true;
    }

    //密码过期
    public boolean isCredentialsNonExpired(){
        return true;
    }

    //账号禁用
    public boolean isEnabled(){
        return true;
    }
}