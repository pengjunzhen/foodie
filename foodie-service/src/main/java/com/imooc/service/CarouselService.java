package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * @author pengjunzhen
 * @description 轮播图类
 * @date 2020/1/18 21:30
 */
public interface CarouselService {

    /**
     * 查询所有的轮播图
     * @param isShow 是否展示的类型
     * @return List<Carousel>
     */
    List<Carousel> queryAll(Integer isShow);
}
