package com.imooc.controller;

import com.imooc.pojo.Orders;
import com.imooc.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/20 00:10
 */
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    /**
     * 支付中心的调用地址 produce
     */
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";		

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     *                          |-> 回调通知的url
     */
    String payReturnUrl = "http://api.z.mukewang.com/foodie-dev-api/orders/notifyMerchantOrderPaid";

    /**
     * 用户上传头像的位置
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "workspaces" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";
//    public static final String IMAGE_USER_FACE_LOCATION = "/workspaces/images/foodie/faces";


//    @Autowired
//    public MyOrdersService myOrdersService;

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     * @return
     */
//    public JSONResult checkUserOrder(String userId, String orderId) {
//        Orders order = myOrdersService.queryMyOrder(userId, orderId);
//        if (order == null) {
//            return JSONResult.errorMsg("订单不存在！");
//        }
//        return JSONResult.ok(order);
//    }
}
