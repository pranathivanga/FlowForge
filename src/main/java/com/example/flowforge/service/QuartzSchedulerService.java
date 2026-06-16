package com.example.flowforge.service;

import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
public class QuartzSchedulerService {

    private final Scheduler scheduler;

    public QuartzSchedulerService(
            Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(
            JobDetail jobDetail,
            Trigger trigger)
            throws SchedulerException {

        scheduler.scheduleJob(
                jobDetail,
                trigger);
    }
}