package com.augusto.rabbitmq.consumerqueue;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerQueue {

    @RabbitListener(queues = "${queue.notification.name}")
    public void receive(@Payload MessageDTO message) {
        System.out.println("Notification: " + message);
    }

//    @RabbitListener(queues = "${queue.notification.name}")
//    public void receive(@Payload MessageDTO message) {
//        throw new AmqpRejectAndDontRequeueException("failed");
//    }

}
