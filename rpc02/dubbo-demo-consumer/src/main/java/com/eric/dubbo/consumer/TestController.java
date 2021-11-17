package com.eric.dubbo.consumer;

import com.eric.dubbo.api.User;
import com.eric.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/16 4:26 PM
 */
@RestController
public class TestController {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @RequestMapping(value = "/get/user", method = RequestMethod.GET)
    public void getUserService() {
        User user = userService.finById(1);
        System.out.println("find user id=1 from server: " + user.getName());
    }
}
