package com.imooc.service;

import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.bo.ShopCartBO;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

import java.util.List;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/19 22:03
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param submitOrderBO 提交的订单参数类
     * @param shopCartList  购物车列表
     * @return OrderVO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO, List<ShopCartBO> shopCartList);

    /**
     * 修改订单状态
     *
     * @param orderId     订单id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId 订单id
     * @return OrderStatus
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    void closeOrder();
}
