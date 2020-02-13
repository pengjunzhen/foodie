package com.imooc.pojo.vo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/12 16:22
 */
@Data
public class CommentLevelCountsVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
