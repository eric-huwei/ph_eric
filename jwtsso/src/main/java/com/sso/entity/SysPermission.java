package com.sso.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/30 2:26 PM
 */
@Data
@Entity
@Table(schema = "ph_eric", name = "sso_sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 4285835478693487481L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "uri")
    private String uri;

    @Column(name = "seq")
    private Integer seq = 1;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    private Date updateTime;
}
