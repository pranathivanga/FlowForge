package com.example.flowforge.repository;

import com.example.flowforge.entity.WorkflowEdge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowEdgeRepository
        extends JpaRepository<WorkflowEdge, Long> {
    List<WorkflowEdge> findBySourceNode_Workflow_Id(Long workflowId);
}