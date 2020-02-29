package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description 用于创建订单的BO对象
 * @date 2020/2/18 22:38
 */
@Data
public class SubmitOrderBO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品规格id
     */
    private String itemSpecIds;

    /**
     * 用户地址id
     */
    private String addressId;

    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 留言
     */
    private String leftMsg;
}
