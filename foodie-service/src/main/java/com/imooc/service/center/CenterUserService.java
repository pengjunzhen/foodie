package com.imooc.service.center;


import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UserVO;


public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return Users
     */
    Users queryUserInfo(String userId);
}
