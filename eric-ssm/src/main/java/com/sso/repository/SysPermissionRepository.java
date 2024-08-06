package com.sso.repository;

import com.sso.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 10:55 AM
 */
public interface SysPermissionRepository extends JpaRepository<SysPermission, Integer> {

    @Query(value = "select * from sso_sys_permission where id in (:ids)", nativeQuery = true)
    List<SysPermission> findByIds(List<Integer> ids);
}
