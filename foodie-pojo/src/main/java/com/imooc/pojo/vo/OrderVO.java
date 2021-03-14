package com.imooc.pojo.vo;

import com.imooc.pojo.bo.ShopCartBO;
import lombok.Data;

import java.util.List;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/19 22:05
 */
@Data
public class OrderVO {

    private String orderId;
    private MerchantOrdersVO merchantOrdersVO;
    private List<ShopCartBO> toBeRemovedShopCartList;
}
