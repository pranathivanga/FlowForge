package com.example.flowforge.service.impl;

import com.example.flowforge.entity.ExecutionStatus;
import com.example.flowforge.entity.Workflow;
import com.example.flowforge.entity.WorkflowExecution;
import com.example.flowforge.repository.WorkflowRepository;
import com.example.flowforge.repository.WorkflowExecutionRepository;
import com.example.flowforge.service.ConnectorExecutionService;
import com.example.flowforge.service.ExecutionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    private final ExecutorService executorService;
    private final WorkflowRepository workflowRepository;
    private final WorkflowExecutionRepository workflowExecutionRepository;
    private final ConnectorExecutionService
            connectorExecutionService;
    public ExecutionServiceImpl(
            WorkflowRepository workflowRepository,
            WorkflowExecutionRepository workflowExecutionRepository,ExecutorService executorService,ConnectorExecutionService connectorExecutionService) {

        this.workflowRepository = workflowRepository;
        this.workflowExecutionRepository = workflowExecutionRepository;
        this.executorService = executorService;
        this.connectorExecutionService = connectorExecutionService;
    }

    @Override
    public WorkflowExecution startExecution(Long workflowId) {

        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found!"));

        WorkflowExecution execution = new WorkflowExecution();
        execution.setWorkflow(workflow);
        execution.setStatus(ExecutionStatus.PENDING);

        WorkflowExecution savedExecution =
                workflowExecutionRepository.save(execution);

        executorService.submit(() -> {
            try {
                savedExecution.setStatus(
                        ExecutionStatus.RUNNING);
                workflowExecutionRepository
                        .save(savedExecution);

                System.out.println("▶️ Node 1 started");

                connectorExecutionService
                        .execute("DEVGUARD");

                Thread.sleep(2000);

                System.out.println("✅ Node 1 finished");

                System.out.println("▶️ Node 2 started");
                Thread.sleep(2000);
                System.out.println("✅ Node 2 finished");

                savedExecution.setStatus(
                        ExecutionStatus.SUCCESS);
                workflowExecutionRepository
                        .save(savedExecution);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        return savedExecution;
    }
    @Override
    public List<WorkflowExecution> getExecutions(
            Long workflowId) {

        return workflowExecutionRepository
                .findByWorkflowId(workflowId);
    }
}