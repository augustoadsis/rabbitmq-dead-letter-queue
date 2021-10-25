package com.augusto.rabbitmq.producerqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerQueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(MessageDTO messageDTO) {
        rabbitTemplate.convertAndSend(this.queue.getName(), messageDTO);
    }

    @RabbitListener(queues = "${queue.notification.dlq-name}")
    public void processFailedMessages(MessageDTO message) {
        System.out.println("Notification failed:" + message);
    }
}
