package com.eric.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午4:04
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String typeId;
    private String typeName;
    private String parentType;
}
