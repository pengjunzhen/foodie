package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description 用户中心，我的订单列表嵌套商品VO
 * @date 2020/3/8 13:51
 */
@Data
public class MySubOrderItemVO {

    private String itemId;
    private String itemImg;
    private String itemName;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}
