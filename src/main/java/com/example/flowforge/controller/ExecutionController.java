package com.example.flowforge.controller;

import com.example.flowforge.entity.WorkflowExecution;
import com.example.flowforge.service.ExecutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/executions")
public class ExecutionController {

    private final ExecutionService executionService;

    public ExecutionController(
            ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/{workflowId}")
    public WorkflowExecution startExecution(
            @PathVariable Long workflowId) {

        return executionService
                .startExecution(workflowId);
    }
    @GetMapping("/{workflowId}")
    public List<WorkflowExecution> getExecutions(
            @PathVariable Long workflowId) {

        return executionService
                .getExecutions(workflowId);
    }
}