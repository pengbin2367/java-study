package com.atqingke.shardingsphere;

import com.atqingke.shardingsphere.entity.User;
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
}
