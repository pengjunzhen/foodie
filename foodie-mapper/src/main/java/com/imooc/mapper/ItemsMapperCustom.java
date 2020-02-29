package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemsMapperCustom {

    /**
     *
     * @param map 查询条件
     * @return List<ItemCommentVO>
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询商品
     * @param map 查询条件
     * @return List<SearchItemsVO>
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询三级分类商品
     * @param map 查询条件
     * @return List<SearchItemsVO>
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    /**
     * 通过规格列表查找 商品
     * @param specIdsList 规格列表
     * @return List<ShopCartVO>
     */
    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);

    /**
     * 减库存
     * @param specId 规格id
     * @param pendingCounts 扣除的库存数
     * @return int
     */
    int decreaseItemSpecStock(@Param("specId") String specId,
                              @Param("pendingCounts") int pendingCounts);
}
