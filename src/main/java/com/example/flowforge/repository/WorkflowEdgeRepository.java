package com.example.flowforge.repository;

import com.example.flowforge.entity.WorkflowEdge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowEdgeRepository
        extends JpaRepository<WorkflowEdge, Long> {
}