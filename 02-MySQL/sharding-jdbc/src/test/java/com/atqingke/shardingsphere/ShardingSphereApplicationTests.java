package com.atqingke.shardingsphere;

import com.atqingke.shardingsphere.entity.Order;
import com.atqingke.shardingsphere.entity.User;
import com.atqingke.shardingsphere.mapper.OrderMapper;
import com.atqingke.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShardingSphereApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testReadWrite() {
        User user = new User();
        user.setId(1000L);
        user.setUname("李斯");
        userMapper.insert(user);
    }

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 垂直分片：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser(){
        User user = new User();
        user.setUname("强哥");
        userMapper.insert(user);
        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(user.getId());
        orderMapper.insert(order);
    }

    /**
     * 垂直分片：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser(){
        User user = userMapper.selectById(1L);
        Order order = orderMapper.selectById(1L);
    }

    @Test
    public void testInsertOrder(){
        Order order = new Order();
        order.setOrderNo("ATGUIGU001");
        order.setUserId(1L);
        orderMapper.insert(order);
    }

    @Test
    public void testInsertOrderDatabaseStrategy(){
        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(i + 1);
            orderMapper.insert(order);
        }
    }

    @Test
    public void testInsertOrderTableStrategy(){
        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(1L);
            orderMapper.insert(order);
        }
        for (long i = 0; i < 4; i++) {
            Order order = new Order();
            order.setOrderNo("ATGUIGU" + System.currentTimeMillis());
            order.setUserId(2L);
            orderMapper.insert(order);
        }
    }
}
