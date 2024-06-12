package com.atqingke.kafka.producer;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class KafkaTest {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Test
    void testSendMessage() {
        kafkaTemplate.send("topic-spring-boot", "hello spring boot message");
    }
}
