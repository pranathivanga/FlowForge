package com.example.flowforge.rabbitmq;

import com.example.flowforge.config.RabbitMQConfig;
import com.example.flowforge.dto.ExecutionMessage;
import com.example.flowforge.service.ExecutionWorkerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ExecutionConsumer {
    private final ExecutionWorkerService workerService;

    public ExecutionConsumer(
            ExecutionWorkerService workerService) {

        this.workerService = workerService;
    }
    @RabbitListener(queues = RabbitMQConfig.EXECUTION_QUEUE)
    public void consumeExecution(
            ExecutionMessage message) {

        System.out.println(
                "📥 Received execution "
                        + message.getExecutionId());

        try {

            workerService.execute(
                    message.getExecutionId());

        }
        catch (Exception e) {

            System.out.println(
                    "❌ Execution Failed: "
                            + message.getExecutionId());

            throw e;
        }
    }
}