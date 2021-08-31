package io.eric.provider;

import io.eric.api.Order;
import io.eric.api.OrderService;
import org.springframework.stereotype.Service;

@Service("io.eric.api.OrderService")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
