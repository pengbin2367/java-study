package com.atqingke.spzx.cloud.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {

    private String dateformat ;
}