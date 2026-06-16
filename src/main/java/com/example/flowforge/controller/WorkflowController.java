package com.example.flowforge.controller;

import com.example.flowforge.dto.request.CreateWorkflowRequest;
import com.example.flowforge.dto.response.ApiResponse;
import com.example.flowforge.dto.response.WorkflowResponse;
import com.example.flowforge.service.WorkflowService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    public ApiResponse<WorkflowResponse> createWorkflow(
            @Valid @RequestBody CreateWorkflowRequest request) {

        WorkflowResponse workflow =
                workflowService.createWorkflow(request);

        return new ApiResponse<>(
                true,
                "Workflow created successfully!",
                workflow
        );
    }
    @PutMapping("/{id}")
    public ApiResponse<WorkflowResponse> updateWorkflow(
            @PathVariable Long id,
            @Valid @RequestBody CreateWorkflowRequest request) {

        WorkflowResponse workflow =
                workflowService.updateWorkflow(id, request);

        return new ApiResponse<>(
                true,
                "Workflow updated successfully!",
                workflow
        );
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteWorkflow(
            @PathVariable Long id) {

        workflowService.deleteWorkflow(id);

        return new ApiResponse<>(
                true,
                "Workflow deleted successfully!",
                null
        );
    }
    @PatchMapping("/{id}/enable")
    public ApiResponse<WorkflowResponse> enableWorkflow(
            @PathVariable Long id) {

        WorkflowResponse workflow =
                workflowService.enableWorkflow(id);

        return new ApiResponse<>(
                true,
                "Workflow enabled successfully!",
                workflow
        );
    }
    @PatchMapping("/{id}/disable")
    public ApiResponse<WorkflowResponse> disableWorkflow(
            @PathVariable Long id) {

        WorkflowResponse workflow =
                workflowService.disableWorkflow(id);

        return new ApiResponse<>(
                true,
                "Workflow disabled successfully!",
                workflow
        );
    }
}