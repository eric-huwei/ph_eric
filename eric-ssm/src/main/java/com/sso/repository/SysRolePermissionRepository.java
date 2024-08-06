package com.sso.repository;

import com.sso.entity.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 10:13 AM
 */
public interface SysRolePermissionRepository extends JpaRepository<SysRolePermission, Integer> {

    @Query(value = "select * from sso_role_permission where role_id in (:roleIds)", nativeQuery = true)
    List<SysRolePermission> findByRoleIds(@Param("roleIds") List<Integer> roleIds);
}
