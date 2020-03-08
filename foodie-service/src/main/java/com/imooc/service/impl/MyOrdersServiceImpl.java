package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.Orders;
import com.imooc.pojo.vo.MyOrdersVO;
import com.imooc.service.MyOrdersService;
import com.imooc.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/19 22:03
 */
@Service
public class MyOrdersServiceImpl implements MyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Autowired
    public OrderStatusMapper orderStatusMapper;

    @Autowired
    public OrdersMapper ordersMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);

        return PagedGridResult.setterPagedGrid(list, page);
    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean updateDeliverOrderStatus(String orderId) {

        // UPDATE order_status SET order_status=?, deliver_time=? WHERE (order_id = ? AND order_status = ?)
        // 相当于 set 部分
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateOrder.setDeliverTime(new Date());

        // 相当于where条件
        UpdateWrapper<OrderStatus> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.eq("order_status", OrderStatusEnum.WAIT_DELIVER.type);

        int result = orderStatusMapper.update(updateOrder, updateWrapper);
        return result == 1;
    }

    @Transactional(propagation=Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Orders queryMyOrder(String userId, String orderId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", orderId);
        return ordersMapper.selectOne(queryWrapper);
    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean updateReceiveOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        // 相当于where条件
        UpdateWrapper<OrderStatus> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", orderId);
        updateWrapper.eq("order_status", OrderStatusEnum.WAIT_RECEIVE.type);

        int result = orderStatusMapper.update(updateOrder, updateWrapper);
        return result == 1;
    }

    @Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteOrder(String userId, String orderId) {
        // UPDATE orders SET is_delete=?, updated_time=? WHERE (id = ? AND user_id = ?)
        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNo.YES.type);
        updateOrder.setUpdatedTime(new Date());

        // 相当于 where 语句
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", orderId);
        updateWrapper.eq("user_id", userId);

        int result = ordersMapper.update(updateOrder, updateWrapper);
        return result == 1;
    }
}
