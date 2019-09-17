package com.jianzhong.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ApiModel
@ToString
public class User implements UserDetails
{
    @ApiModelProperty(value = "用户ID")
    @Getter @Setter
    private Long uid;

    @ApiModelProperty(value = "用户名")
    @Setter @Getter
    private String username;

    @ApiModelProperty(value = "密码")
    @Setter @Getter
    private String password;

    @ApiModelProperty(value = "是否删除")
    @Setter @Getter
    private Integer is_del;

    @ApiModelProperty(value = "创建时间")
    @Setter @Getter
    private Integer create_time;

    @ApiModelProperty(value = "更新时间")
    @Setter @Getter
    private Integer update_time;

    @ApiModelProperty(value = "角色")
    @Setter @Getter
    private String roles;

    @ApiModelProperty(value = "是否启用")
    @Setter @Getter
    private Integer is_enabled;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @ApiModelProperty(value = "邮件")
    @Setter @Getter
    private List<UserMail> userMails;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnoreProperties
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        ArrayList<String> roleList = new ArrayList<>(Arrays.asList(roles.split(",")));
        for (String role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role));
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