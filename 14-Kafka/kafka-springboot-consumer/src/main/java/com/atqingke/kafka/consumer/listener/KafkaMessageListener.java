package com.atqingke.kafka.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

//    @KafkaListener(topics = {"topic-spring-boot"})
//    public void simpleConsumerPartition(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
//        System.out.println(record.partition() + " " + record.value());
//        // 消息处理完成后，手动提交
//        acknowledgment.acknowledge();
//    }

//    @RetryableTopic
//    @KafkaListener(topics = {"topic-spring-boot"})
//    public void simpleConsumerPartition(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
//        System.out.println(record.partition() + " " + record.offset() + " " + record.value());
//
//        System.out.println(10 / 0);
//
//        // 消息处理完成后，手动提交
//        acknowledgment.acknowledge();
//    }
//
//    // 死信主题会自动在原主题名称后附加“-dlt”
//    @KafkaListener(topics = {"topic-spring-boot-dlt"})
//    public void deadLetterProcess(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
//
//        System.out.println("死信主题：" + record.partition() + " " + record.offset() + " " + record.value());
//
//        // 消息处理完成后，手动提交
//        acknowledgment.acknowledge();
//    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(
                    topic = "topic-spring-boot",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0"),
                            @PartitionOffset(partition = "1", initialOffset = "50"),
                            @PartitionOffset(partition = "2", initialOffset = "0", relativeToCurrent = "true")
                    }
            )
    })
    public void simpleConsumerPartition(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println(record.partition() + " " + record.offset() + " " + record.value());

        // 消息处理完成后，手动提交
        acknowledgment.acknowledge();
    }
}