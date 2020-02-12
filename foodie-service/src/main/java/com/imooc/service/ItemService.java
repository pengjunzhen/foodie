package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;

import java.util.List;

/**
 * @author pengjunzhen
 * @description 商品服务类
 * @date 2020/2/12 11:27
 */

public interface ItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId 商品ID
     * @return Items
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品ID查询图片列表
     * @param itemId 商品ID
     * @return List<ItemsImg>
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品ID查询规格
     * @param itemId 商品ID
     * @return List<ItemsSpec>
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品ID查询商品参数
     * @param itemId 商品ID
     * @return ItemsParam
     */
    ItemsParam queryItemParam(String itemId);
}
