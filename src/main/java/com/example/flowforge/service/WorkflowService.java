package com.example.flowforge.service;

import com.example.flowforge.dto.request.CreateWorkflowRequest;
import com.example.flowforge.dto.response.WorkflowGraphResponse;
import com.example.flowforge.dto.response.WorkflowResponse;

public interface WorkflowService {

    WorkflowResponse createWorkflow(CreateWorkflowRequest request);
    WorkflowResponse updateWorkflow(
            Long id,
            CreateWorkflowRequest request
    );
    void deleteWorkflow(Long id);
    WorkflowResponse enableWorkflow(Long id);

    WorkflowResponse disableWorkflow(Long id);
    WorkflowGraphResponse getWorkflowGraph(
            Long workflowId);
}