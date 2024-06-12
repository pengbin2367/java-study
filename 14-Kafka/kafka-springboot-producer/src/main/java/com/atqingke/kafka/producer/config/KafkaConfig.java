package com.atqingke.kafka.producer.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic springTestTopic() {
        return TopicBuilder.name("topic-spring-boot") // 主题名称
                .partitions(3) // 分区数量
                .replicas(3) // 副本数量
                .build();
    }
    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Resource
    private KafkaProducerInterceptor kafkaProducerInterceptor;

    @PostConstruct
    public void init() {
        kafkaTemplate.setProducerInterceptor(kafkaProducerInterceptor);
        System.out.println("----init success over,KafkaTemplate配置生产者拦截器kafkaProducerInterceptor");
    }
}