package com.example.flowforge.service.impl;

import com.example.flowforge.entity.ExecutionStatus;
import com.example.flowforge.entity.WorkflowExecution;
import com.example.flowforge.repository.WorkflowExecutionRepository;
import com.example.flowforge.service.ConnectorExecutionService;
import com.example.flowforge.service.ExecutionWorkerService;
import org.springframework.stereotype.Service;

@Service
public class ExecutionWorkerServiceImpl
        implements ExecutionWorkerService {

    private final WorkflowExecutionRepository
            executionRepository;

    private final ConnectorExecutionService
            connectorExecutionService;

    public ExecutionWorkerServiceImpl(
            WorkflowExecutionRepository executionRepository,
            ConnectorExecutionService connectorExecutionService) {

        this.executionRepository = executionRepository;
        this.connectorExecutionService =
                connectorExecutionService;
    }

    @Override
    public void execute(Long executionId) {

        WorkflowExecution execution =
                executionRepository.findById(executionId)
                        .orElseThrow(() ->
                                new RuntimeException("Execution not found"));

        try {

            execution.setStatus(
                    ExecutionStatus.RUNNING);

            executionRepository.save(execution);

            System.out.println("▶️ Node 1 Started");

            connectorExecutionService.execute("DEVGUARD");

            Thread.sleep(2000);

            System.out.println("✅ Node 1 Finished");

            System.out.println("▶️ Node 2 Started");

            Thread.sleep(2000);

            System.out.println("✅ Node 2 Finished");

            execution.setStatus(
                    ExecutionStatus.SUCCESS);

            executionRepository.save(execution);

        }
        catch (Exception e) {

            execution.setStatus(
                    ExecutionStatus.FAILED);

            executionRepository.save(execution);

            throw new RuntimeException(e);
        }
    }
}