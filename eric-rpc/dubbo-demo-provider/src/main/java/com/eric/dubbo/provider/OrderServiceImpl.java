package com.eric.dubbo.provider;

import com.eric.dubbo.api.Order;
import com.eric.dubbo.api.OrderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/8 2:41 PM
 */
@DubboService(version = "1.0.0")
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
