package com.eric.dubbo.provider;

import com.eric.dubbo.api.User;
import com.eric.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/8 2:42 PM
 */
@DubboService(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Override
    public User finById(int id) {
        return new User(id, "eric " + System.currentTimeMillis());
    }
}
