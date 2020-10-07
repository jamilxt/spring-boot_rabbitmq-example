package com.jamilxt.rabbitmq.demo.publisher;

import com.jamilxt.rabbitmq.demo.config.MessagingConfig;
import com.jamilxt.rabbitmq.demo.domain.Order;
import com.jamilxt.rabbitmq.demo.domain.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    private final RabbitTemplate template;

    public OrderPublisher(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        // restaurant Service
        // payment Service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Order placed successfully in " + restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success!";
    }

}
