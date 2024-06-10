package com.atqingke.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_order")
@Data
public class Order {
//    @TableId(type = IdType.AUTO)
//    ## 注意：水平分片的id需要在业务层实现，不能依赖数据库的主键自增
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String orderNo;
    private Long userId;
}