package com.atqingke.spzx.cloud.order.service;

import com.atqingke.spzx.cloud.order.entity.Order;

public interface OrderService {

    // 根据订单的id查询订单数据
    public abstract Order findOrderByOrderId(Long orderId) ;

}