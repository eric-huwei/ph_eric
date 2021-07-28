package com.eric.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/5/9 下午5:11
 */
@Configuration
public class Application {


    @Component
    public static class Person {
        @Autowired
        Man man;

        Person() {
            System.out.println("开始创建person");
        }
    }

    @Component
    public static class Man {

        private String name;

        Man() {
            name = "man name";
            System.out.println("开始创建man");
        }
    }
}