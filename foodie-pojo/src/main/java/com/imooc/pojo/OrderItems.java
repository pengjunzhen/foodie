package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "order_items")
public class OrderItems {
    /**
     * 主键id
     */
    @TableId(type = IdType.NONE)
    private String id;

    /**
     * 归属订单id
     */
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 商品id
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 商品图片
     */
    @TableField(value = "item_img")
    private String itemImg;

    /**
     * 商品名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 规格id
     */
    @TableField(value = "item_spec_id")
    private String itemSpecId;

    /**
     * 规格名称
     */
    @TableField(value = "item_spec_name")
    private String itemSpecName;

    /**
     * 成交价格
     */
    private Integer price;

    /**
     * 购买数量
     */
    @TableField(value = "buy_counts")
    private Integer buyCounts;

}