package com.eric.dubbo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/8 1:47 PM
 */
@Setter
@Getter
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
}
