package com.atqingke.spzx.cloud.feign.fallback;

import com.atqingke.spzx.cloud.feign.api.UserFeignClient;
import com.atqingke.spzx.cloud.model.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public User queryById(Long userId) {
        log.info("UserFeignClientFallback...queryById...方法执行了...");
        return new User();
    }
    
}