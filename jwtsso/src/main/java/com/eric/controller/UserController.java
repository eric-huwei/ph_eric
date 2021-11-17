package com.eric.controller;

import com.eric.entity.User;
import com.eric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午3:05
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    public User addUser(@RequestBody User user) {

        return userService.saveUser(user);
    }
}
