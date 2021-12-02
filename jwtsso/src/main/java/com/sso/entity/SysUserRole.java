package com.sso.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 9:51 AM
 */
@Data
@Entity
@Table(schema = "ph_eric", name = "sso_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = -1810195806444298544L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;
}
