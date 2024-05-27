package com.atqingke.spzx.cloud.order.service;

import com.atqingke.spzx.cloud.model.entity.order.Order;

public interface OrderService {

    // 根据订单的id查询订单数据
    public abstract Order findOrderByOrderId(Long orderId) ;

}