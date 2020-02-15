package com.imooc.pojo.bo;

import lombok.Data;

/**
 * @author pengjunzhen
 * @description 用户新增或修改地址的BO
 * @date 2020/2/15 19:21
 */
@Data
public class AddressBO {

    private String addressId;

    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
