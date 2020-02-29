package com.imooc.config;

import com.imooc.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author pengjunzhen
 * @description 订单定时任务相关
 * @date 2020/2/24 22:06
 */
@Component
public class OrderJob {

//    @Scheduled(cron = "0/3 * * * * ?")
//    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder() {
        System.out.println("执行定时任务，当前时间为："
                + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
}
