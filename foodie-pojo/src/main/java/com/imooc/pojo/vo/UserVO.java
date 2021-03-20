package com.imooc.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjunzhen
 */
@ApiModel(value = "用户对象VO", description = "从客户端，由用户传入的数据封装在此entity中")
@Data
public class UserVO {

    /**
     * 主键id 用户id
     */
    @ApiModelProperty(value = "id", name = "id", example = "1", required = false)
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "jack", required = true)
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickname", example = "jack")
    private String nickname;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456")
    private String confirmPassword;

    /**
     * 头像 头像
     */
    private String face;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /**
     * 用户会话token
     */
    @ApiModelProperty(value = "用户会话token", name = "userUniqueToken")
    private String userUniqueToken;

}
