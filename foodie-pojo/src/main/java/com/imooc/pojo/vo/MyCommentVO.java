package com.imooc.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/3/11 01:52
 */
@Data
public class MyCommentVO {

    private String commentId;
    private String content;
    private Date createdTime;
    private String itemId;
    private String itemName;
    private String specName;
    private String itemImg;
}
