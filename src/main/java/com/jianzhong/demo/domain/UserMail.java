package com.jianzhong.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@ApiModel
@ToString
public class UserMail
{
    @ApiModelProperty(value = "主键ID")
    @Setter @Getter
    private Long user_mail_id;

    @ApiModelProperty(value = "用户ID")
    @Setter @Getter
    private Long uid;

    @ApiModelProperty(value = "邮件地址")
    @Setter @Getter
    private String mail;

    @ApiModelProperty(value = "是否删除")
    @Setter @Getter
    private Integer is_del;

    @ApiModelProperty(value = "创建时间")
    @Setter @Getter
    private Integer create_time;

    @ApiModelProperty(value = "更新时间")
    @Setter @Getter
    private Integer update_time;

}