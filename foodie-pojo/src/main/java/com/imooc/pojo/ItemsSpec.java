package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName(value = "items_spec")
public class ItemsSpec {
    /**
     * 商品规格id
     */
    @TableId(type = IdType.NONE)
    private String id;

    /**
     * 商品外键id
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 折扣力度
     */
    private BigDecimal discounts;

    /**
     * 优惠价
     */
    @TableField(value = "price_discount")
    private Integer priceDiscount;

    /**
     * 原价
     */
    @TableField(value = "price_normal")
    private Integer priceNormal;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

}