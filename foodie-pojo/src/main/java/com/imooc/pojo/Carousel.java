package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author pengjunzhen
 */
@Data
@TableName(value = "carousel")
public class Carousel {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图片 图片地址
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 背景色 背景颜色
     */
    @TableField(value = "background_color")
    private String backgroundColor;

    /**
     * 商品id 商品id
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 商品分类id 商品分类id
     */
    @TableField(value = "cat_id")
    private Long catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    private Integer type;

    /**
     * 轮播图展示顺序 轮播图展示顺序，从小到大
     */
    private Integer sort;

    /**
     * 是否展示 是否展示，1：展示    0：不展示
     */
    @TableField(value = "is_show")
    private Integer isShow;

    /**
     * 创建时间 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间 更新
     */
    @TableField(value = "update_time")
    private Date updateTime;

}