package com.atqingke.spzx.cloud.user;

import com.atqingke.spzx.cloud.user.properties.PatternProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableConfigurationProperties(value = { PatternProperties.class })
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class , args) ;
    }

}