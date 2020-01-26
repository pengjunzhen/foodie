package com.imooc.enums;

import lombok.Data;

/**
 * 是否枚举类
 * @author pengjunzhen
 */
public enum YesOrNo {
    /**
     * 0： 否
     */
    NO(0, "否"),
    /**
     * 1： 是
     */
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
