package com.atqingke.spzx.cloud.order.service.impl;

import com.atqingke.spzx.cloud.order.entity.Order;
import com.atqingke.spzx.cloud.order.entity.User;
import com.atqingke.spzx.cloud.order.feign.UserFeignClient;
import com.atqingke.spzx.cloud.order.mapper.OrderMapper;
import com.atqingke.spzx.cloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper ;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    UserFeignClient userFeignClient;

    @Override
    public Order findOrderByOrderId(Long orderId) {
        Order order = orderMapper.findOrderByOrderId(orderId);

        User user = userFeignClient.queryById(order.getUserId());
        order.setUser(user);

        return order;
    }

    private ServiceInstance findServiceInstance(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        Random random = new Random();
        return instances.get(random.nextInt(instances.size()));
    }
}