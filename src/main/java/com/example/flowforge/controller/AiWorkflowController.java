package com.example.flowforge.controller;

import com.example.flowforge.dto.request.AiWorkflowRequest;
import com.example.flowforge.dto.response.AiWorkflowResponse;
import com.example.flowforge.service.AiWorkflowService;
import com.example.flowforge.service.GroqService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiWorkflowController {
    private final GroqService groqService;
    private final AiWorkflowService aiWorkflowService;

    public AiWorkflowController(
            AiWorkflowService aiWorkflowService,GroqService groqService) {

        this.aiWorkflowService =
                aiWorkflowService;
        this.groqService =groqService;
    }
    @PostMapping("/groq-test")
    public String testGroq(
            @RequestBody
            AiWorkflowRequest request) {

        return groqService
                .generateWorkflow(
                        request.getPrompt());
    }
    @PostMapping("/generate")
    public AiWorkflowResponse generateWorkflow(
            @RequestBody AiWorkflowRequest request) {

        return aiWorkflowService
                .generateWorkflow(
                        request.getPrompt());
    }
    @PostMapping("/generate-save")
    public Long generateAndSave(
            @RequestBody
            AiWorkflowRequest request) {

        return aiWorkflowService
                .generateAndSaveWorkflow(
                        request.getPrompt());
    }
}