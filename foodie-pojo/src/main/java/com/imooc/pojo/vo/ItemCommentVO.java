package com.imooc.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pengjunzhen
 * @description 用于展示商品评价的VO
 * @date 2020/2/13 21:23
 */
@Data
public class ItemCommentVO {

    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;
    private String nickname;
}
