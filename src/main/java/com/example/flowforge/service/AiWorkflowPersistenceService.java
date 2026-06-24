package com.example.flowforge.service;

import com.example.flowforge.dto.response.AiWorkflowResponse;

public interface AiWorkflowPersistenceService {

    Long saveWorkflow(
            AiWorkflowResponse workflowResponse);

}