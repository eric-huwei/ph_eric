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
     * ๆ้ๅ่กจ
     */
    private List<String> authorityList;
}
