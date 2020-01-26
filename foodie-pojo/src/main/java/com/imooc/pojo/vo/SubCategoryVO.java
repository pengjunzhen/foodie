package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/1/26 17:19
 */
@Data
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
