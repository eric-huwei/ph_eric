package com.sso.service.impl;

import com.sso.entity.SysUser;
import com.sso.repository.SysUserRepository;
import com.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/30 4:37 PM
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
