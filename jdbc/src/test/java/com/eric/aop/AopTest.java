package com.eric.aop;

import com.eric.mybatis.entity.User;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/5/7 下午8:27
 */
@SpringBootTest
public class AopTest {

    @Test
    public void testCglib() {
    }

    @Test
    public void testInitBean() {
        User user = new User();

        user.getAge();
    }
}