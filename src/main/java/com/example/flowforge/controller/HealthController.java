package com.example.flowforge.controller;

import com.example.flowforge.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public ApiResponse<String> healthCheck() {
        return new ApiResponse<>(
                true,
                "FlowForge backend is running!",
                "🦋 Debut Era unlocked."
        );
    }

    @GetMapping("/api/test-error")
    public ApiResponse<String> testError() {
        throw new RuntimeException("This is a test exception!");
    }
}