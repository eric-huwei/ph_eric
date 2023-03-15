package com.sso.repository;

import com.sso.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/30 4:44 PM
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    SysUser findByUsername(String username);

}
