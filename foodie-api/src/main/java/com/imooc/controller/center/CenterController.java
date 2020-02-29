package com.imooc.controller.center;

import com.imooc.service.center.CenterUserService;
import com.imooc.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;

/**
 * @author pengjunzhen
 * @description 用户中心
 * @date 2020/2/29 15:38
 */
@Api(value = "center - 用户", tags = {"用户中心展示的相关接口"})
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @GetMapping("/userInfo")
    public JSONResult userInfo(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {
        return JSONResult.ok(centerUserService.queryUserInfo(userId));
    }

}
