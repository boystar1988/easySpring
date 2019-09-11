package com.jianzhong.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.annotations.Cache;

@Entity
@Cache(region="common", usage = CacheConcurrencyStrategy.READ_WRITE)
@Setter @Getter @ToString
@Table(name = "sp_user")
public class User implements UserDetails {

    @ApiModelProperty(value = "用户ID")
    @Id @Column(name = "uid", nullable = false)
    private Long uid;

    @ApiModelProperty(value = "用户名")
    @Column(columnDefinition = "varchar(32) comment '用户名'")
    private String username;

    @ApiModelProperty(value = "密码")
    @Column(columnDefinition = "varchar(48) comment '密码'")
    private String password;

    @ApiModelProperty(value = "创建时间")
    private int create_time;

    @ApiModelProperty(value = "更新时间")
    private int update_time;

    @ApiModelProperty(value = "是否删除")
    private int is_del;

    @ApiModelProperty(value = "是否启用")
    private int is_enabled;

    @ApiModelProperty(value = "角色")
    private String roles;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

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