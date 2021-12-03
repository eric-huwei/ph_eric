package com.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 2:01 PM
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
