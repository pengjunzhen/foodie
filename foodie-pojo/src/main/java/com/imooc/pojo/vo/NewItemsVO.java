package com.imooc.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author pengjunzhen
 * @description 最新商品VO
 * @date 2020/1/26 19:11
 */
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
