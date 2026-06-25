package com.example.flowforge.rabbitmq;

import com.example.flowforge.config.RabbitMQConfig;
import com.example.flowforge.dto.ExecutionMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExecutionProducer {

    private final RabbitTemplate rabbitTemplate;

    public ExecutionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendExecution(ExecutionMessage message) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXECUTION_EXCHANGE,
                RabbitMQConfig.EXECUTION_ROUTING_KEY,
                message);

        System.out.println(
                "📤 Execution queued: "
                        + message.getExecutionId());
    }
}