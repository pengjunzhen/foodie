package com.imooc.service;

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
}
