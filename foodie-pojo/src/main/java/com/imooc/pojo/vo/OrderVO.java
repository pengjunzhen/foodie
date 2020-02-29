package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/19 22:05
 */
@Data
public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
}
