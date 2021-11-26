package com.sso.vo;

import com.sso.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/26 2:51 PM
 */
@Data
public class SysUserVO extends SysUser {
    /**
     * 权限列表
     */
    private List<String> authorityList;
}
