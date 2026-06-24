package com.example.flowforge.repository;

import com.example.flowforge.entity.WorkflowNode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowNodeRepository
        extends JpaRepository<WorkflowNode, Long> {
}