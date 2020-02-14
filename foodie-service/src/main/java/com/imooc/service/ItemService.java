package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopCartVO;
import com.imooc.utils.PagedGridResult;

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


    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 查询评论信息（分页）
     * @param itemId 商品ID
     * @param level 评价等级
     * @param page 页数
     * @param pageSize 每页大小
     * @return PagedGridResult
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param keywords 搜索关键字
     * @param sort 排序
     * k: 默认，代表默认排序，根据name
     * c: 根据销量排序
     * p: 根据价格排序
     * @param page 当前页数
     * @param pageSize 每页显示数量
     * @return PagedGridResult
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     * @param catId 分类Id
     * @param sort 排序
     * k: 默认，代表默认排序，根据name
     * c: 根据销量排序
     * p: 根据价格排序
     * @param page 当前页数
     * @param pageSize 每页显示数量
     * @return PagedGridResult
     */
    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
     * @param itemSpecIds 商品规格id列表
     * @return List<ShopCartVO>
     */
    List<ShopCartVO> queryItemsBySpecIds(String itemSpecIds);
}
