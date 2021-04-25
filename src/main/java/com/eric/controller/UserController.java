package com.eric.controller;

import com.eric.entity.User;
import com.eric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/4/25 下午10:25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/user/getUserInfo?id=1
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(HttpServletRequest request) {
        User user = userService.getUserInfo(Integer.parseInt(request.getParameter("id")));
        return user;
    }
}
