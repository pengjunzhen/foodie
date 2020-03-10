package com.imooc.service.center;

import com.imooc.pojo.OrderItems;
import com.imooc.pojo.bo.OrderItemsCommentBO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * 订单评价接口
 */
public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId 订单id
     * @return List<OrderItems>
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId 订单id
     * @param userId 用户id
     * @param commentList 评价列表
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);


    /**
     * 我的评价查询 分页
     * @param userId 用户id
     * @param page 当前页
     * @param pageSize 煤业规格
     * @return PagedGridResult
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
