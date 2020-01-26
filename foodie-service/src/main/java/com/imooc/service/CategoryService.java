package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return List<Category>
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId 一级分类id
     * @return List<CategoryVO>
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId 一级分类id
     * @return List<NewItemsVO>
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
