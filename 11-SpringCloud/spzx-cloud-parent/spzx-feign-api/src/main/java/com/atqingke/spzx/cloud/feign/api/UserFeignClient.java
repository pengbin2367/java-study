package com.atqingke.spzx.cloud.feign.api;

import com.atqingke.spzx.cloud.feign.fallback.UserFeignClientFallback;
import com.atqingke.spzx.cloud.model.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "spzx-cloud-user"/*, fallback = UserFeignClientFallback.class*/)		// 声明当前接口是一个访问user-service的feign的客户端
public interface UserFeignClient {

    @GetMapping("/api/user/findUserByUserId/{userId}")
    public abstract User queryById(@PathVariable("userId") Long userId) ;	// 根据userId查询用户信息的接口方法
}