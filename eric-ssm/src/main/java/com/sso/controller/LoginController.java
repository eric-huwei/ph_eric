package com.sso.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 2:01 PM
 */
@RestController
@Slf4j
@RequestMapping("/ssm")
public class LoginController {

    @GetMapping("/testTraceId")
    public String testTraceId() {
        new Thread(() -> log.info("testTraceId")).start();
        return "testTraceId";
    }
}
