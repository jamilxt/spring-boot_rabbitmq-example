package com.jamilxt.rabbitmq.demo.consumer;

import com.jamilxt.rabbitmq.demo.config.MessagingConfig;
import com.jamilxt.rabbitmq.demo.domain.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue: " + orderStatus);
    }

}
