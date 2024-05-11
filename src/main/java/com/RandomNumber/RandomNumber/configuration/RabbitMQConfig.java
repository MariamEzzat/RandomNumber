package com.RandomNumber.RandomNumber.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue randomNumbersQueue() {
        return new Queue("randomNumbersQueue");
    }
}
