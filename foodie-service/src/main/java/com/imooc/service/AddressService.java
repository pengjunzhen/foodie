package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/15 19:17
 */
public interface AddressService {

    /**
     * 根据用户id查找用户的收货地址列表
     * @param userId 用户Id
     * @return List<UserAddress>
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     * @param addressBO 参数包装类
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改地址
     * @param addressBO 参数包装类
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 根据 用户id 和 地址id, 删除对应的用户地址信息
     * @param userId 用户id
     * @param addressId 地址id
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 根据 用户id 和 地址id, 修改默认地址
     * @param userId 用户id
     * @param addressId 地址id
     */
    void updateUserAddressToDefault(String userId, String addressId);

    /**
     * 根据 用户id 和 地址id, 查询具体用户地址对象信息
     * @param userId 用户id
     * @param addressId 地址id
     */
    UserAddress queryUserAddress(String userId, String addressId);
}
