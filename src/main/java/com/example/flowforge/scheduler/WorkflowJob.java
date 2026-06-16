package com.example.flowforge.scheduler;

import com.example.flowforge.service.ExecutionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WorkflowJob implements Job {
    private final ExecutionService executionService;

    public WorkflowJob(
            ExecutionService executionService) {
        this.executionService = executionService;
    }
    @Override
    public void execute(
            JobExecutionContext context)
            throws JobExecutionException {

        System.out.println(
                "🚀 Quartz triggered workflow execution at: "
                        + LocalDateTime.now());

        try {
            executionService.startExecution(2L);
            System.out.println(
                    "✅ Workflow execution created!");
        } catch (Exception e) {
            System.out.println(
                    "❌ Failed to start workflow: "
                            + e.getMessage());
        }
    }
}