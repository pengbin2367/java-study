package com.atqingke.spzx.cloud.order.service.impl;

import com.atqingke.spzx.cloud.order.entity.Order;
import com.atqingke.spzx.cloud.order.entity.User;
import com.atqingke.spzx.cloud.order.mapper.OrderMapper;
import com.atqingke.spzx.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper ;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order findOrderByOrderId(Long orderId) {
        Order order = orderMapper.findOrderByOrderId(orderId);

        User user = restTemplate.getForObject("http://localhost:10100/api/user/findUserByUserId/" + order.getUserId(), User.class);
        order.setUser(user);

        return order;
    }

}