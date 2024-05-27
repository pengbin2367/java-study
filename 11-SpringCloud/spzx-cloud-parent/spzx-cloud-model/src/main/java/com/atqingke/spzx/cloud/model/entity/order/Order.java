package com.atqingke.spzx.cloud.model.entity.order;

import com.atqingke.spzx.cloud.model.entity.user.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private Long id ;
    private Long userId ;
    private String name ;
    private BigDecimal price ;
    private Integer num ;
    private User user;
}