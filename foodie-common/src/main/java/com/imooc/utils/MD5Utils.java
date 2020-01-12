package com.imooc.utils;

import org.springframework.util.DigestUtils;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/1/4 22:11
 */
public class MD5Utils {
    /**
     * 获取md5字符串
     * @param strValue 输入字符串
     * @return String
     */
    public static String getMD5Str(String strValue) {

        return DigestUtils.md5DigestAsHex(strValue.getBytes());
    }

    public static void main(String[] args) {
        try {
            System.out.println(getMD5Str("imooc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
