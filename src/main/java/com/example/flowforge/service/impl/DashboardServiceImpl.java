package com.example.flowforge.service.impl;

import com.example.flowforge.dto.response.DashboardResponse;
import com.example.flowforge.repository.WorkflowExecutionRepository;
import com.example.flowforge.repository.WorkflowNodeRepository;
import com.example.flowforge.repository.WorkflowRepository;
import com.example.flowforge.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final WorkflowRepository workflowRepository;

    private final WorkflowNodeRepository workflowNodeRepository;

    private final WorkflowExecutionRepository executionRepository;

    public DashboardServiceImpl(
            WorkflowRepository workflowRepository,
            WorkflowNodeRepository workflowNodeRepository,
            WorkflowExecutionRepository executionRepository) {

        this.workflowRepository = workflowRepository;
        this.workflowNodeRepository = workflowNodeRepository;
        this.executionRepository = executionRepository;
    }

    @Override
    public DashboardResponse getDashboard() {

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalWorkflows(
                workflowRepository.count());

        response.setTotalNodes(
                workflowNodeRepository.count());

        response.setTotalExecutions(
                executionRepository.count());

        long active =
                workflowRepository.findAll()
                        .stream()
                        .filter(w -> w.isEnabled())
                        .count();

        response.setActiveWorkflows(active);

        response.setDisabledWorkflows(
                response.getTotalWorkflows() - active);

        return response;
    }
}