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
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "jack", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456", required = false)
    private String confirmPassword;

}
