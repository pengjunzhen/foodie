package com.imooc.controller;

import com.imooc.Constant;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.ShopCartBO;
import com.imooc.pojo.vo.UserVO;
import com.imooc.service.UserService;
import com.imooc.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        // 1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不能为空");
        }

        // 2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已存在");
        }

        // 3.请求成功，用户名没有重复
        return JSONResult.ok(userService.queryUsernameIsExist(username));
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserVO userVO,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        String username = userVO.getUsername();
        String password = userVO.getPassword();
        String confirmPwd = userVO.getConfirmPassword();

        // 1、判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 2、密码长度不能少于6位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能小于6");
        }

        // 3、判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return JSONResult.errorMsg("两次密码输入不一致");
        }

        // 4、实现注册
        Users userResult = userService.createUser(userVO);
//        userResult = setNullProperty(userResult);

        // 实现用户的redis会话
        UserVO userVo = convertUserVo(userResult);
        // 注意，这里严格意义上来讲，是需要加密存储的，这里是因为为了方便测试，所以没有加密
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userVo), true);

        // 同步购物车数据
        synchorizeShopcartData(userResult.getId(), request, response);

        return JSONResult.ok();
    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserVO userVO,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String username = userVO.getUsername();
        String password = userVO.getPassword();

        // 1、判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 2、密码长度不能少于6位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能小于6");
        }

        // 3、登陆
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return JSONResult.errorMsg("用户名或密码错误");
        }

        UserVO userVo = convertUserVo(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userVo), true);

        // 同步购物车数据
        synchorizeShopcartData(userResult.getId(), request, response);

        return JSONResult.ok(userResult);
    }

    /**
     * 注册登录成功后，同步cookie和redis中的购物车数据
     */
    private void synchorizeShopcartData(String userId, HttpServletRequest request,
                                        HttpServletResponse response) {

        /**
         * 1. Redis中无数据，如果cookie中的购物车为空，则不做处理
         *                 如果cookie中的购物车不为空，此时将cookie中的数据放入Redis中。
         * 2. Redis中有数据，如果cookie中的购物车为空，则将Redis中的购物车数据覆盖本地的Cookie.
         *                 如果cookie中的购物车不为空，
         *                      如果cookie中的某个商品在Redis中存在， 则以Cookie为主，删除Redis中的，
         *                      把Cookie中的商品直接覆盖Redis中（参考京东）。
         *                      cookie中存在，Redis中不存在的商品，直接将Cookie中的数据放进去。
         * 3. 同步到Redis中去之后，覆盖本地Cookie购物车的数据，保证本地购物车的数据是同步最新的。
         */

        // 从redis中获取购物车
        String shopCartRedisKey = Constant.FOODIE_SHOPCART + ":" + userId;
        String shopcartJsonRedis = redisOperator.get(shopCartRedisKey);

        // 从cookie中获取购物车
        String shopcartStrCookie = CookieUtils.getCookieValue(request, Constant.FOODIE_SHOPCART, true);

        if (StringUtils.isEmpty(shopcartJsonRedis)) {
            // redis为空，cookie不为空，直接把cookie中的数据放入redis
            if (StringUtils.isNotEmpty(shopcartStrCookie)) {
                redisOperator.set(shopCartRedisKey, shopcartStrCookie);
            }
        } else {
            // redis不为空，cookie不为空，合并cookie和redis中购物车的商品数据（同一商品则覆盖redis）
            if (StringUtils.isNotEmpty(shopcartStrCookie)) {

                /**
                 * 1. 已经存在的，把cookie中对应的数据量，覆盖Redis（参考京东）
                 * 2. 该项商品标记为待删除，统一放入一个待删除的list
                 * 3. 从cookie中清理掉所有的待删除list
                 * 4. 合并redis和cookie中的数据
                 * 5. 更新到Redis和Cookie中
                 */
                List<ShopCartBO> shopcartListRedis = JsonUtils.jsonToList(shopcartJsonRedis, ShopCartBO.class);
                List<ShopCartBO> shopcartListCookie = JsonUtils.jsonToList(shopcartStrCookie, ShopCartBO.class);

                // 定义一个待删除list
                List<ShopCartBO> pendingDeleteList = new ArrayList<>();

                for (ShopCartBO redisShopcart : shopcartListRedis) {
                    String redisSpecId = redisShopcart.getSpecId();

                    for (ShopCartBO cookieShopcart : shopcartListCookie) {
                        String cookieSpecId = cookieShopcart.getSpecId();

                        if (redisSpecId.equals(cookieSpecId)) {
                            // 覆盖购买数量，不累加，参考京东
                            redisShopcart.setBuyCounts(cookieShopcart.getBuyCounts());
                            // 把cookieShopcart放入待删除列表，用于最后的删除与合并
                            pendingDeleteList.add(cookieShopcart);
                        }

                    }
                }
                // 从现有cookie中删除对应的覆盖过的商品数据
                shopcartListCookie.removeAll(pendingDeleteList);

                // 合并两个list
                shopcartListRedis.addAll(shopcartListCookie);
                // 更新到redis和cookie
                CookieUtils.setCookie(request, response, Constant.FOODIE_SHOPCART, JsonUtils.objectToJson(shopcartListRedis), true);
                redisOperator.set(shopCartRedisKey, JsonUtils.objectToJson(shopcartListRedis));
            } else {
                // redis不为空，cookie为空，直接把redis覆盖cookie
                CookieUtils.setCookie(request, response, Constant.FOODIE_SHOPCART, shopcartJsonRedis, true);
            }

        }
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        // 清除用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        // 用户退出登录，清除redis中user的会话信息
        redisOperator.del(Constant.REDIS_USER_TOKEN + ":" + userId);

        // 分布式会话中需要清除用户数据
        CookieUtils.deleteCookie(request, response, Constant.FOODIE_SHOPCART);

        return JSONResult.ok();
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
