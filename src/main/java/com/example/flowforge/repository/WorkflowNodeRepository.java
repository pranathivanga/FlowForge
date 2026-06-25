package com.example.flowforge.repository;

import com.example.flowforge.entity.WorkflowNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowNodeRepository
        extends JpaRepository<WorkflowNode, Long> {
    List<WorkflowNode> findByWorkflowId(Long workflowId);
}