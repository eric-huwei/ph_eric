package com.eric.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午3:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setFirstName("Hu");
        user.setLastName("Eric");

        userService.saveUser(user);
    }
}