package com.augusto.rabbitmq.producerqueue;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Value("${queue.notification.name}")
    private String NOTIFICATION_QUEUE;
    @Value("${queue.notification.exchange}")
    private String NOTIFICATION_EXCHANGE;

    @Value("${queue.notification.dlq-name}")
    private String DLQ_NOTIFICATION_QUEUE;
    @Value("${queue.notification.dlq-exchange}")
    private String DLQ_NOTIFICATION_EXCHANGE;

    @Value("${queue.notification.routing-key}")
    private String ROUTING_KEY_NOTIFICATION_QUEUE;

    @Bean
    Queue queue() {
        return QueueBuilder.durable(NOTIFICATION_QUEUE)
                .withArgument("x-dead-letter-exchange", DLQ_NOTIFICATION_EXCHANGE)
                .build();
    }

    @Bean
    DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    Binding bindingNotification() {
        return BindingBuilder.bind(queue()).to(notificationExchange()).with(ROUTING_KEY_NOTIFICATION_QUEUE);
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(DLQ_NOTIFICATION_EXCHANGE);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(DLQ_NOTIFICATION_QUEUE).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate notificationRabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
