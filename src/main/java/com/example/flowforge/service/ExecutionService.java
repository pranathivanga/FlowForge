package com.example.flowforge.service;

import com.example.flowforge.entity.WorkflowExecution;

import java.util.List;

public interface ExecutionService {

    WorkflowExecution startExecution(Long workflowId);
    List<WorkflowExecution> getExecutions(
            Long workflowId);
}