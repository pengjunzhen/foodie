package com.imooc.service.center;

import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.OrderStatusCountsVO;
import com.imooc.utils.PagedGridResult;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/3/8 13:58
 */
public interface MyOrdersService {

    /**
     * 查询指定用户的订单列表
     * @param userId 用户id
     * @param orderStatus 订单状态
     * @param page 当前页码
     * @param pageSize 每页规格
     * @return PagedGridResult
     */
    PagedGridResult queryMyOrders(String userId,
                                  Integer orderStatus,
                                  Integer page,
                                  Integer pageSize);

    /**
     * 更新订单状态为已发货
     * @param orderId 订单id
     */
    Boolean updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     * @param userId 用户id
     * @param orderId 订单id
     * @return Orders
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态 —> 确认收货
     * @return boolean
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId 用户id
     * @param orderId 订单id
     * @return boolean
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId 用户id
     */
    public OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId 用户id
     * @param page 当前页码
     * @param pageSize 每页规格
     * @return PagedGridResult
     */
    PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);
}
