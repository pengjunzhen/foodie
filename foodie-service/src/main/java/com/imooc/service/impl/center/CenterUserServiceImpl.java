package com.imooc.service.impl.center;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.enums.Sex;
import com.imooc.mapper.UserMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.vo.UserVO;
import com.imooc.service.UserService;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users queryUserInfo(String userId) {
        return userMapper.selectById(userId);
    }
}
