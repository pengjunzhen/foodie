package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Items {
    /**
     * 商品主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品 code
     */
    @TableField(value = "item_code")
    private String itemCode;

    /**
     * 商品名称 商品名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 分类外键id 分类id
     */
    @TableField(value = "cat_id")
    private Long catId;

    /**
     * 一级分类外键id 一级分类id，用于优化查询
     */
    @TableField(value = "root_cat_id")
    private Long rootCatId;

    /**
     * 累计销售 累计销售
     */
    @TableField(value = "sell_counts")
    private Integer sellCounts;

    /**
     * 上下架状态 上下架状态,1:上架 2:下架
     */
    @TableField(value = "on_off_status")
    private Integer onOffStatus;

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