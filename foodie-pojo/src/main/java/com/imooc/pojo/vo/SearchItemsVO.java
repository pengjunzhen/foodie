package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description 查询商品的包装类
 * @date 2020/2/14 15:47
 */
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}
