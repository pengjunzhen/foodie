package com.imooc.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/1/26 17:18
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;

    /**
     * 三级分类vo list
     */
    private List<SubCategoryVO> subCatList;
}
