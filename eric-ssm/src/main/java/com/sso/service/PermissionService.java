package com.sso.service;

import com.sso.entity.SysPermission;

import java.util.List;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/30 2:23 PM
 */
public interface PermissionService {

    List<SysPermission> findByUserId(int userId);
}
