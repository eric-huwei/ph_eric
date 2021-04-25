package com.eric.service;

import com.eric.entity.User;
import com.eric.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/4/25 下午10:11
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }
}
