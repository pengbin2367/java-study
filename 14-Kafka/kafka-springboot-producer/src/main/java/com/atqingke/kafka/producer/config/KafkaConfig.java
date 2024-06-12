package com.atqingke.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic springTestTopic() {
        return TopicBuilder.name("topic-spring-boot") // 主题名称
                .partitions(3) // 分区数量
                .replicas(3) // 副本数量
                .build();
    }
}