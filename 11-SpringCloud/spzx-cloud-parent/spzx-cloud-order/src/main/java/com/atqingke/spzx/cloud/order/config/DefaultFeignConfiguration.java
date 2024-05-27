package com.atqingke.spzx.cloud.order.config;

import feign.Logger;

// 全局生效 @EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration .class)
// 局部生效 @FeignClient(value = "spzx-cloud-user", configuration = DefaultFeignConfiguration .class)
public class DefaultFeignConfiguration  {
    // @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.BASIC; // 日志级别为BASIC
    }
}