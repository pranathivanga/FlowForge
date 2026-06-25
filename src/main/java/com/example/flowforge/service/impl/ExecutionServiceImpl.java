package com.example.flowforge.service.impl;

import com.example.flowforge.entity.ExecutionStatus;
import com.example.flowforge.entity.Workflow;
import com.example.flowforge.entity.WorkflowExecution;
import com.example.flowforge.rabbitmq.ExecutionProducer;
import com.example.flowforge.repository.WorkflowRepository;
import com.example.flowforge.repository.WorkflowExecutionRepository;
import com.example.flowforge.service.ConnectorExecutionService;
import com.example.flowforge.service.ExecutionService;
import org.springframework.stereotype.Service;
import com.example.flowforge.rabbitmq.ExecutionProducer;
import com.example.flowforge.dto.ExecutionMessage;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    private final ExecutionProducer executionProducer;
    private final WorkflowRepository workflowRepository;
    private final WorkflowExecutionRepository workflowExecutionRepository;
    private final ConnectorExecutionService
            connectorExecutionService;
    public ExecutionServiceImpl(
            WorkflowRepository workflowRepository,
            WorkflowExecutionRepository workflowExecutionRepository,
            ConnectorExecutionService connectorExecutionService,
            ExecutionProducer executionProducer) {

        this.workflowRepository = workflowRepository;
        this.workflowExecutionRepository = workflowExecutionRepository;
        this.connectorExecutionService = connectorExecutionService;
        this.executionProducer = executionProducer;
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

        executionProducer.sendExecution(
                new ExecutionMessage(
                        savedExecution.getId(),
                        workflow.getId()
                ));

        return savedExecution;
    }
    @Override
    public List<WorkflowExecution> getExecutions(
            Long workflowId) {

        return workflowExecutionRepository
                .findByWorkflowId(workflowId);
    }
}