package com.atqingke.kafka.producer;

import com.atqingke.kafka.producer.entity.UserDTO;
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
        kafkaTemplate.send("topic-spring-boot", "hello spring boot message1111");
    }

    @Test
    public void testSendEntity() {
        String topicName = "topic-spring-boot";
        UserDTO userDTO = new UserDTO("tom", 25, "12345343");
        kafkaTemplate.send(topicName, userDTO);
    }
}
