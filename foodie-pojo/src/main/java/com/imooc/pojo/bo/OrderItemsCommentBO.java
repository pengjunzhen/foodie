package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description 订单商品评价接收类
 * @date 2020/3/11 01:36
 */
@Data
public class OrderItemsCommentBO {

    private String commentId;
    private String itemId;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private Integer commentLevel;
    private String content;
}
