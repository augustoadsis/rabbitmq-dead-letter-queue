package com.augusto.rabbitmq.consumerqueue;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ConsumerQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerQueueApplication.class, args);
    }

}
