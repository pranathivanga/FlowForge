package com.example.flowforge.repository;

import com.example.flowforge.entity.WorkflowExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowExecutionRepository
        extends JpaRepository<WorkflowExecution, Long> {
    List<WorkflowExecution> findByWorkflowId(Long workflowId);
}