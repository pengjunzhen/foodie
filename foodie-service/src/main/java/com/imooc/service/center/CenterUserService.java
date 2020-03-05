package com.imooc.service.center;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.pojo.vo.UserVO;


public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return Users
     */
    Users queryUserInfo(String userId);

    /**
     * 通过用户id 更新用户信息
     * @param userId 用户id
     * @param centerUserBO 用户信息包装类
     * @return Users
     */
    Users updateInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 更新用户头像url到数据库中
     * @param userId 用户id
     * @param faceUrl 用户头像地址
     * @return Users
     */
    Users updateUserFace(String userId, String faceUrl);
}
