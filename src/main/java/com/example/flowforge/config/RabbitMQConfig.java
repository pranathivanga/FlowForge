package com.example.flowforge.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitMQConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(
                messageConverter);

        return rabbitTemplate;
    }
    public static final String EXECUTION_QUEUE =
            "workflow.execution.queue";
    public static final String EXECUTION_EXCHANGE =
            "workflow.execution.exchange";

    public static final String EXECUTION_ROUTING_KEY =
            "workflow.execution";

    public static final String DLQ =
            "workflow.execution.dlq";

    public static final String DLQ_ROUTING_KEY =
            "workflow.execution.dlq";
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXECUTION_EXCHANGE);
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(executionQueue())
                .to(exchange())
                .with(EXECUTION_ROUTING_KEY);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(exchange())
                .with(DLQ_ROUTING_KEY);
    }
    @Bean
    public Queue executionQueue() {

        return QueueBuilder
                .durable(EXECUTION_QUEUE)
                .deadLetterExchange(EXECUTION_EXCHANGE)
                .deadLetterRoutingKey(DLQ_ROUTING_KEY)
                .build();
    }
}