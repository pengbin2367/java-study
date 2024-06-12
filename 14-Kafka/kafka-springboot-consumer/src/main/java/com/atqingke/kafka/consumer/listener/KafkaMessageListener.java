package com.atqingke.kafka.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = {"topic-spring-boot"})
    public void simpleConsumerPartition(ConsumerRecord<String, String> record) {
        System.out.println("进入simpleConsumer方法");
        System.out.printf(
                "分区 = %d, 偏移量 = %d, key = %s, 内容 = %s, 时间戳 = %d%n",
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
    }
}