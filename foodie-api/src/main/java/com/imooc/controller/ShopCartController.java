package com.imooc.controller;

import com.imooc.Constant;
import com.imooc.pojo.bo.ShopCartBO;
import com.imooc.utils.JSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengjunzhen
 * @description 购物车接口controller
 * @date 2020/2/14 21:23
 */
@Api(value = "购物车接口controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
@RestController
public class ShopCartController {

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId,
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest request,
            HttpServletResponse response
            ) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        System.out.println(shopCartBO);

        // 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        // 需要判断当前购物车中包含已经存在的商品，如果存在则累加购买数量
        String shopCartRedisKey = Constant.FOODIE_SHOPCART + ":" + userId;
        String shopCartJson = redisOperator.get(shopCartRedisKey);
        List<ShopCartBO> shopcartList = null;
        if (StringUtils.isNotEmpty(shopCartJson)) {
            // redis中已经有购物车了
            shopcartList = JsonUtils.jsonToList(shopCartJson, ShopCartBO.class);
            boolean exist = false;
            // 判断购物车中是否存在已有商品，如果有的话counts累加
            for (ShopCartBO cartBO : shopcartList) {
                String itemId = cartBO.getItemId();
                if (itemId.equals(shopCartBO.getItemId())) {
                    cartBO.setBuyCounts(cartBO.getBuyCounts() + shopCartBO.getBuyCounts());
                    exist = true;
                }
                if (!exist) {
                    shopcartList.add(shopCartBO);
                }
            }
        } else {
            // redis中没有购物车，商品 直接添加到购物车中
            shopcartList = new ArrayList<>();
            shopcartList.add(shopCartBO);
        }
        // 覆盖现有redis中的购物车
        redisOperator.set(shopCartRedisKey, JsonUtils.objectToJson(shopcartList));
        return JSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public JSONResult del(
            @RequestParam String userId,
            @RequestBody String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        // 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除redis购物车中的商品
        String shopCartRedisKey = Constant.FOODIE_SHOPCART + ":" + userId;
        String shopCartJson = redisOperator.get(shopCartRedisKey);
        if (StringUtils.isNotBlank(shopCartJson)) {
            // redis中已经有购物车了
            List<ShopCartBO> shopcartList = JsonUtils.jsonToList(shopCartJson, ShopCartBO.class);
            // 判断购物车中是否存在已有商品，如果有的话则删除
            for (ShopCartBO sc: shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(itemSpecId)) {
                    shopcartList.remove(sc);
                    break;
                }
            }
            // 覆盖现有redis中的购物车
            redisOperator.set(shopCartRedisKey, JsonUtils.objectToJson(shopcartList));
        }

        return JSONResult.ok();
    }
}
