package com.imooc.service;


import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UserVO;


public interface UserService {

    /**
     * 当前用户名是否存在
     * @param username 用户名
     * @return true: 存在，false：不存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userVO 用户传输类
     * @return Users
     */
    Users createUser(UserVO userVO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param username 用户名
     * @param password 密码
     * @return Users
     */
    Users queryUserForLogin(String username, String password);
}
