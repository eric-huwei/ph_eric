package com.eric.dubbo.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/8 1:38 PM
 */
@Setter
@Getter
@AllArgsConstructor
public class Order {

    private int id;
    private String name;
    private float amout;
}
