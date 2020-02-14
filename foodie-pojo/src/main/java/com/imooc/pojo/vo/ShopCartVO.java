package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/14 21:28
 */
@Data
public class ShopCartVO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String priceDiscount;
    private String priceNormal;
}
