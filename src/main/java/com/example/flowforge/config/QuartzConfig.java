package com.example.flowforge.config;

import com.example.flowforge.scheduler.WorkflowJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail workflowJobDetail() {
        return JobBuilder.newJob(WorkflowJob.class)
                .withIdentity("workflowJob")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger workflowJobTrigger(
            JobDetail workflowJobDetail) {

        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(20)
                        .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(workflowJobDetail)
                .withIdentity("workflowTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}