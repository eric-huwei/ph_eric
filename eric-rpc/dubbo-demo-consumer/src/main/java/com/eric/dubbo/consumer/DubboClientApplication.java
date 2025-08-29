package com.eric.dubbo.consumer;

import com.eric.dubbo.api.Order;
import com.eric.dubbo.api.OrderService;
import com.eric.dubbo.api.User;
import com.eric.dubbo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/8 2:47 PM
 */
@SpringBootApplication
public class DubboClientApplication {

    @DubboReference(version = "1.0.0")
    private UserService userService;

    @DubboReference(version = "1.0.0")
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            User user = userService.finById(1);
            System.out.println("find user id=1 from server: " + user.getName());

            Order order = orderService.findOrderById(1);
            System.out.println(String.format("find order name = %s, amount = %f", order.getName(), order.getAmout()));
        };
    }
}
