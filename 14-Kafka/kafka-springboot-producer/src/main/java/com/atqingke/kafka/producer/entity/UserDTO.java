package com.atqingke.kafka.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private Integer age;
    private String mobile;
}