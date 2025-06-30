package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTestComponent implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitSender rabbitSender;

    @Override
    public void run(String... args) throws Exception {
        // Write data to Redis
        redisTemplate.opsForValue().set("test:key", "Hello Redis!");

        // Read data from Redis
        String value = redisTemplate.opsForValue().get("test:key");
        System.out.println("Read from Redis: " + value);

        // Send message to RabbitMQ
        rabbitSender.send("Hello MQ!");
    }
}
