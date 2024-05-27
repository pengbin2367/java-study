package com.atqingke.spzx.cloud.user.mapper;

import com.atqingke.spzx.cloud.model.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper  // 该注解可以通过在启动类上的@MapperScan注解进行替换
public interface UserMapper {

    // 根据用户的id查询用户详情
    User findUserByUserId(Long userId) ;

}