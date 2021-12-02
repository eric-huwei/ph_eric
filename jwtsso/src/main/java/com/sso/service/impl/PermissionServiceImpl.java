package com.sso.service.impl;

import com.sso.entity.SysPermission;
import com.sso.entity.SysRolePermission;
import com.sso.entity.SysUserRole;
import com.sso.repository.SysPermissionRepository;
import com.sso.repository.SysRolePermissionRepository;
import com.sso.repository.SysUserRoleRepository;
import com.sso.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 9:49 AM
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Override
    public List<SysPermission> findByUserId(int userId) {
        List<SysUserRole> sysUserRoleList = sysUserRoleRepository.findByUserId(userId);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            return null;
        }
        List<Integer> roleIdList = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        List<SysRolePermission> rolePermissionList = sysRolePermissionRepository.findByRoleIds(roleIdList);
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return null;
        }
        List<Integer> permissionIdList = rolePermissionList.stream().map(SysRolePermission::getPermissionId).distinct().collect(Collectors.toList());
        List<SysPermission> sysPermissionList = sysPermissionRepository.findByIds(permissionIdList);
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            return null;
        }
        return sysPermissionList;
    }
}
