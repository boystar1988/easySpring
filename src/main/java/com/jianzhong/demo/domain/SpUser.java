package com.jianzhong.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Data
@Entity
@Table(name = "sp_user")
public class SpUser {

    @Id
    @Column(name = "uid", nullable = false)
    @Setter
    @Getter
    private Long uid;

    @Setter
    @Getter
    @Column(columnDefinition = "varchar(32) comment '用户名'")
    private String username;

    @Setter
    @Getter
    @Column(columnDefinition = "varchar(48) comment '密码'")
    private String password;

    @Setter
    @Getter
    private int create_time;
    @Setter
    @Getter
    private int update_time;
    @Setter
    @Getter
    private int is_del;
    @Setter
    @Getter
    private int is_enabled;
    @Setter
    @Getter
    private String roles;

    @Override
    public String toString() {
        return
                "uid:"+uid+",username: " + username + ", password: " + password + ",roles: " + roles + ",create_time: "+create_time+ ",update_time: "+update_time+ ",is_del: "+is_del+ ",is_enabled: "+is_enabled;
    }

}