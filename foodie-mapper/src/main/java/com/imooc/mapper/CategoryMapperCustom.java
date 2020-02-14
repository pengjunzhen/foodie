package com.imooc.mapper;

import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunzhen
 * @description 自定义的分类mapper
 * @date 2020/1/26 17:18
 */
@Repository
public interface CategoryMapperCustom {

    /**
     * 获取子分类列表
     * @param rootCatId 一级分类列表
     * @return List<CategoryVO>
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param paramsMap 参数列表
     * @return List<NewItemsVO>
     */
    List<NewItemsVO> getSixNewItemsLazy(Map<String, Object> paramsMap);
}
