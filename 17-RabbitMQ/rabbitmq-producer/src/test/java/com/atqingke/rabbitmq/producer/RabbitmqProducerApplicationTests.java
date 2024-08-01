package com.atqingke.rabbitmq.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class RabbitmqProducerApplicationTests {

    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";

    public static final String EXCHANGE_TX = "exchange.tx.dragon";
    public static final String ROUTING_TX = "routing.key.tx.dragon";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    @Transactional
    @Rollback(value = true)
    public void testSendMessageInTx() {
        // 1、发送第一条消息
        rabbitTemplate.convertAndSend(EXCHANGE_TX, ROUTING_TX, "I am a dragon(tx msg [rollback] ~~~01)");
        // 2、发送第二条消息
        rabbitTemplate.convertAndSend(EXCHANGE_TX, ROUTING_TX, "I am a dragon(tx msg [rollback] ~~~02)");
    }

    @Test
    public void testSendMessage() {
        // 1、创建消息后置处理器对象
        MessagePostProcessor messagePostProcessor = (Message message) -> {
            // 设定 TTL 时间，以毫秒为单位
            message.getMessageProperties().setExpiration("5000");
            return message;
        };

        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_DIRECT,
                    ROUTING_KEY,
                    "Hello RabbitMQ" + i,
                    messagePostProcessor);
        }
    }
}
