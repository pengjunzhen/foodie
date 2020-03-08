package com.imooc.mapper;

import com.imooc.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunzhen
 * @description 自定义相关订单查询接口
 * @date 2020/3/8 13:37
 */
@Repository
public interface OrdersMapperCustom {

    /**
     * 查询我的订单
     * @param map 参数
     * @return List<MyOrdersVO>
     */
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap")Map<String, Object> map);
}
