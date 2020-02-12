package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/12 12:01
 */
@Api(value = "商品接口", tags = {"商品信息展示"})
@RestController
@RequestMapping("items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult info(
            @ApiParam(name = "itmeId", value = "商品Id", required = true)
            @PathVariable String itemId
    ) {
        if (StringUtils.isBlank(itemId)) {
            return JSONResult.errorMsg("商品ID为空");
        }

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(itemService.queryItemById(itemId));
        itemInfoVO.setItemImgList(itemService.queryItemImgList(itemId));
        itemInfoVO.setItemParams(itemService.queryItemParam(itemId));
        itemInfoVO.setItemSpecList(itemService.queryItemSpecList(itemId));

        return JSONResult.ok(itemInfoVO);
    }
}
