package com.atqingke.kafka.producer.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AtQingKePartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        // 排除消息值为空的情况
        if (value == null) {
            throw new RuntimeException("消息值不能为空！");
        }

        // 排除消息不是字符串类型的情况
        if (!(value instanceof String)) {
            return 2;
        }

        // 需求：消息数据特征决定它进入哪个分区
        // 包含atguigu，发送到：0
        // 包含sgg，发送到：1
        // 其它，发送到：2
        String message = (String) value;

        if (message.contains("atqingke"))
            return 0;

        if (message.contains("pengbin"))
            return 1;

        return 2;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}