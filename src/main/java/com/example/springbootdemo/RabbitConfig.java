package com.example.springbootdemo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue testQueue() {
        return new Queue("test-queue", true); // durable queue
    }
}
