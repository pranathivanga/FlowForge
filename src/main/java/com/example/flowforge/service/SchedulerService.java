package com.example.flowforge.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchedulerService {

//    @Scheduled(cron = "0/15 * * * * ?")
    public void heartbeat() {
        System.out.println(
                "🕛 FlowForge Scheduler Alive: "
                        + LocalDateTime.now());
    }
}