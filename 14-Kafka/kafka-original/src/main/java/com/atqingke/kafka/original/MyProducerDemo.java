package com.atqingke.kafka.original;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class MyProducerDemo {
    public static final String TOPIC_NAME = "topic-java-client";

    public static void main(String[] args)
    {
        // 1. 创建Kafka生产者的配置对象
        Properties properties = new Properties();

        // 2. 给Kafka配置对象添加配置信息：bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.0.166:9092");

        // key,value序列化（必须）：key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // 3. 创建Kafka生产者对象
        KafkaProducer kafkaProducer = new KafkaProducer(properties);

        // 4. 调用send方法,发送消息
        for (int i = 0; i < 5; i++) {
//            kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, "hello-kafka-from-java-client~" + i));
            kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, "hello-kafka-from-java-client*******"), new Callback() {

                // onCompletion() 方法在发送消息操作完成时被调用
                // 参数 RecordMetadata recordMetadata：发送消息相关的元数据
                // 参数 Exception e：发送消息失败时，失败原因封装的异常信息
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {

                    if (e == null) {
                        long offset = recordMetadata.offset();
                        System.out.println("offset = " + offset);

                        int partition = recordMetadata.partition();
                        System.out.println("partition = " + partition);

                        long timestamp = recordMetadata.timestamp();
                        System.out.println("timestamp = " + timestamp);

                        String topic = recordMetadata.topic();
                        System.out.println("topic = " + topic);

                    } else {
                        System.out.println("e = " + e);
                    }
                }
            });
        }

        System.out.println("----MyProducerDemo发送完毕");
        // 5. 关闭资源
        kafkaProducer.close();
    }
}