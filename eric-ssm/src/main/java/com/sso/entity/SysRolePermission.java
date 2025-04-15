package com.sso.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 10:03 AM
 */
@Data
@Entity
//@Table(schema = "ph_eric", name = "sso_role_permission")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 5707968605982393118L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;
}
