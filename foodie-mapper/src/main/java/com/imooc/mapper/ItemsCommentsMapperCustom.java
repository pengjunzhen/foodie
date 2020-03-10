package com.imooc.mapper;

import com.imooc.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/3/11 01:50
 */
@Repository
public interface ItemsCommentsMapperCustom {

    /**
     * 保存订单评论
     * @param map 查询参数
     */
    void saveComments(Map<String, Object> map);

    /**
     * 查询订单
     * @param map 查询参数
     * @return List<MyCommentVO>
     */
    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);
}
