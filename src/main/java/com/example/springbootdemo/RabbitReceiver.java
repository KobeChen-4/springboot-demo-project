package com.example.springbootdemo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @RabbitListener(queues = "test-queue")
    public void receive(String message) {
        System.out.println("Received message from RabbitMQ: " + message);
    }
}
