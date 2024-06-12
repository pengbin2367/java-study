package com.atqingke.kafka.producer;

import com.atqingke.kafka.producer.entity.UserDTO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

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

    @Test
    public void testSendMessageRoundRobin() {
        String topicName = "topic-spring-boot";
        for (int i = 0; i < 50; i++) {
            kafkaTemplate.send(topicName, null, null, new UserDTO("robin" + i, i*10, i*100+""));
        }
    }

    @Test
    public void testMyPartitioner() {
        String topicName = "topic-spring-boot";
        kafkaTemplate.send(topicName, "zhu zhu atqingke zhu");
        kafkaTemplate.send(topicName, "zhu zhu pengbin zhu");
        kafkaTemplate.send(topicName, "zhu zhu happy zhu");
    }

    @Test
    public void testSendMessageOrder() throws ExecutionException, InterruptedException {
        String topicName = "topic-spring-boot";
        // 通过 partition 限定消息发送到同一个分区
        Integer partition = 0;
        String key = null;
        // 为什么要调用 get() 方法？
        // 并不是为了获取任务执行的结果
        // 而是确保第一个任务执行完成之后，再执行第二个任务
        // 如果不这样做，各个子线程负责发送消息没办法保证发送消息的顺序
        kafkaTemplate.send(topicName, partition, key, new UserDTO("order01", 10, "1111")).get();
        kafkaTemplate.send(topicName, partition, key, new UserDTO("order02", 20, "2222")).get();
        kafkaTemplate.send(topicName, partition, key, new UserDTO("order03", 30, "3333")).get();
    }

    @Test
    public void testSendMessageNoOrder() throws ExecutionException, InterruptedException {
        String topicName = "topic-spring-boot";

        for (int i = 0; i < 18; i++) {
            // 每条消息发送到不同分区，看看消费端接收消息的顺序
            kafkaTemplate.send(topicName, i % 3, null, new UserDTO("order" + i, i * 10, i * 100 + "")).get();
        }
    }
}
