package com.example.flowforge.service.impl;

import com.example.flowforge.dto.request.CreateWorkflowRequest;
import com.example.flowforge.dto.response.WorkflowResponse;
import com.example.flowforge.entity.User;
import com.example.flowforge.entity.Workflow;
import com.example.flowforge.repository.UserRepository;
import com.example.flowforge.repository.WorkflowRepository;
import com.example.flowforge.service.WorkflowService;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements WorkflowService {
    private final UserRepository userRepository;
    private final WorkflowRepository workflowRepository;

    public WorkflowServiceImpl(
            WorkflowRepository workflowRepository,
            UserRepository userRepository) {

        this.workflowRepository = workflowRepository;
        this.userRepository = userRepository;
    }

    @Override
    public WorkflowResponse createWorkflow(CreateWorkflowRequest request) {

        Workflow workflow = new Workflow();
        workflow.setName(request.getName());
        workflow.setDescription(request.getDescription());
        User user = userRepository.findById(1L)
                .orElseThrow(() ->
                        new RuntimeException("User not found!"));

        workflow.setUser(user);
        Workflow savedWorkflow = workflowRepository.save(workflow);

        return new WorkflowResponse(
                savedWorkflow.getId(),
                savedWorkflow.getName(),
                savedWorkflow.getDescription(),
                savedWorkflow.isEnabled(),
                savedWorkflow.getVersion()
        );
    }
    @Override
    public WorkflowResponse updateWorkflow(
            Long id,
            CreateWorkflowRequest request) {

        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found!"));

        workflow.setName(request.getName());
        workflow.setDescription(request.getDescription());
        workflow.setVersion(workflow.getVersion() + 1);
        Workflow updatedWorkflow = workflowRepository.save(workflow);

        return new WorkflowResponse(
                updatedWorkflow.getId(),
                updatedWorkflow.getName(),
                updatedWorkflow.getDescription(),
                updatedWorkflow.isEnabled(),
                updatedWorkflow.getVersion()
        );
    }
    @Override
    public void deleteWorkflow(Long id) {

        if (!workflowRepository.existsById(id)) {
            throw new RuntimeException("Workflow not found!");
        }

        workflowRepository.deleteById(id);
    }
    @Override
    public WorkflowResponse enableWorkflow(Long id) {

        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found!"));

        workflow.setEnabled(true);

        Workflow updatedWorkflow = workflowRepository.save(workflow);

        return new WorkflowResponse(
                updatedWorkflow.getId(),
                updatedWorkflow.getName(),
                updatedWorkflow.getDescription(),
                updatedWorkflow.isEnabled(),
                updatedWorkflow.getVersion()
        );
    }

    @Override
    public WorkflowResponse disableWorkflow(Long id) {

        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Workflow not found!"));

        workflow.setEnabled(false);

        Workflow updatedWorkflow = workflowRepository.save(workflow);

        return new WorkflowResponse(
                updatedWorkflow.getId(),
                updatedWorkflow.getName(),
                updatedWorkflow.getDescription(),
                updatedWorkflow.isEnabled(),
                updatedWorkflow.getVersion()
        );
    }
}