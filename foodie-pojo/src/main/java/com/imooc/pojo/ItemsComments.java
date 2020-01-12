package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 商品评论表
 * @author pengjunzhen
 */
@Data
@TableName(value = "items_comments")
public class ItemsComments {
    /**
     * id主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id 用户名须脱敏
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 商品id
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 商品名称
     */
    @TableField(value = "item_name")
    private String itemName;

    /**
     * 商品规格id 可为空
     */
    @TableField(value = "item_spec_id")
    private Long itemSpecId;

    /**
     * 规格名称 可为空
     */
    @TableField(value = "sepc_name")
    private String sepcName;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */
    @TableField(value = "comment_level")
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;

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