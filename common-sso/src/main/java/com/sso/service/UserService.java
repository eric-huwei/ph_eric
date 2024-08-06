package com.sso.service;

import com.sso.entity.SysUser;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/30 2:22 PM
 */
public interface UserService {

    SysUser getByUsername(String username);
}