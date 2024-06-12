package com.atqingke.kafka.original;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MyConsumerDemo
{
    public static final String TOPIC_NAME = "topic-java-client";
    public static void main(String[] args) throws InterruptedException
    {
        // 1、创建Kafka消费者的配置对象
        Properties properties = new Properties();
        
		// 2、给Kafka配置对象添加配置信息：bootstrap.servers
        properties.put("bootstrap.servers", "192.168.0.166:9092");
        properties.setProperty("group.id", "test");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 3、创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        
        // 4、订阅指定主题
        consumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true) {
            // 5、从broker拉取信息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            
            // 6、每隔 1 秒做一次打印，让消费端程序持续运行
            TimeUnit.SECONDS.sleep(1);
            System.out.println("....进行中");
        }
    }
}