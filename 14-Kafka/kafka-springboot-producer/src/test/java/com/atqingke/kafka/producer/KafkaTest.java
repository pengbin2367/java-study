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

    @Test
    public void testSendMessageToPartition() {
        String topicName = "topic-spring-boot";
        UserDTO userDTO = new UserDTO("tom0001", 10, "1111111111");
        // 精确指定某一个分区的下标
        Integer partition = 0;
        // 此时不需要提供 key 值
        Object key = null;
        // 虽然没有提供 key 值，但是必须传入 key 参数
        kafkaTemplate.send(topicName, partition, key, userDTO);
    }

    @Test
    public void testSendMessageByKey() {
        String topicName = "topic-spring-boot";
        UserDTO userDTO = new UserDTO("jerry0002", 20, "2222222222222");
        // 不指定分区，key 才能生效
        Integer partition = null;
        // 指定 key
        // String key = "f"; // 0
        // String key = "a"; // 1
        String key = "b"; // 2
        // 发送消息
        kafkaTemplate.send(topicName, partition, key, userDTO);
    }

    @Test
    public void testSendEntitySticky () {
        String topicName = "topic-spring-boot";
        for (int i = 0; i < 15; i++) {
            UserDTO userDTO = new UserDTO("harry"+i, i*10, i*100 +"");
            kafkaTemplate.send(topicName, userDTO);
        }
    }
}
