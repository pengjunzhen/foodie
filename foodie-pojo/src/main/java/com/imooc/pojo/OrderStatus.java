package com.imooc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "order_status")
public class OrderStatus {


    /**
     * 订单ID 对应订单表的主键id
     */

    @TableId(type = IdType.AUTO)
    @TableField(value = "order_id")
    private String orderId;

    /**
     * 订单状态
     */
    @TableField(value = "order_status")
    private Integer orderStatus;

    /**
     * 订单创建时间 对应[10:待付款]状态
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 支付成功时间 对应[20:已付款，待发货]状态
     */
    @TableField(value = "pay_time")
    private Date payTime;

    /**
     * 发货时间 对应[30：已发货，待收货]状态
     */
    @TableField(value = "deliver_time")
    private Date deliverTime;

    /**
     * 交易成功时间 对应[40：交易成功]状态
     */
    @TableField(value = "success_time")
    private Date successTime;

    /**
     * 交易关闭时间 对应[50：交易关闭]状态
     */
    @TableField(value = "close_time")
    private Date closeTime;

    /**
     * 留言时间 用户在交易成功后的留言时间
     */
    @TableField(value = "comment_time")
    private Date commentTime;

}