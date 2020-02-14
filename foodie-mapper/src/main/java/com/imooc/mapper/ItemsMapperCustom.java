package com.imooc.mapper;

import com.imooc.pojo.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemsMapperCustom {

    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
}
