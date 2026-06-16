package com.example.flowforge.controller;

import com.example.flowforge.scheduler.WorkflowJob;
import com.example.flowforge.service.QuartzSchedulerService;
import org.quartz.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    private final QuartzSchedulerService quartzSchedulerService;
    private final Scheduler scheduler;

    public SchedulerController(
            QuartzSchedulerService quartzSchedulerService,Scheduler scheduler) {
        this.quartzSchedulerService = quartzSchedulerService;
        this.scheduler = scheduler;
    }

    @PostMapping("/start")
    public String startScheduler() throws SchedulerException {

        JobDetail jobDetail =
                JobBuilder.newJob(WorkflowJob.class)
                        .withIdentity(
                                "dynamicWorkflowJob",
                                "flowforge")
                        .build();

        Trigger trigger =
                TriggerBuilder.newTrigger()
                        .withIdentity(
                                "dynamicWorkflowTrigger",
                                "flowforge")
                        .withSchedule(
                                SimpleScheduleBuilder
                                        .simpleSchedule()
                                        .withIntervalInSeconds(30)
                                        .repeatForever())
                        .startNow()
                        .build();

        quartzSchedulerService
                .scheduleJob(jobDetail, trigger);

        return "✅ Workflow scheduled successfully!";
    }
    @GetMapping("/status")
    public String schedulerStatus()
            throws SchedulerException {

        int jobs =
                scheduler.getJobKeys(
                                GroupMatcher.anyJobGroup())
                        .size();

        return "🕛 Scheduler Running | Registered Jobs: "
                + jobs;
    }
}