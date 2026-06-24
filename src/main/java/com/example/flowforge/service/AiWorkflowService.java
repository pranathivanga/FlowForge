package com.example.flowforge.service;

import com.example.flowforge.dto.response.AiWorkflowResponse;

public interface AiWorkflowService {
    Long generateAndSaveWorkflow(
            String prompt);
    AiWorkflowResponse generateWorkflow(
            String prompt);

}