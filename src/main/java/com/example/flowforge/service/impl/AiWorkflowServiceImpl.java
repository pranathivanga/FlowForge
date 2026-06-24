package com.example.flowforge.service.impl;

import com.example.flowforge.dto.AiEdgeDto;
import com.example.flowforge.dto.AiNodeDto;
import com.example.flowforge.dto.response.AiWorkflowResponse;
import com.example.flowforge.service.AiWorkflowPersistenceService;
import com.example.flowforge.service.AiWorkflowService;
import com.example.flowforge.service.GroqService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class AiWorkflowServiceImpl
        implements AiWorkflowService {

    private final GroqService groqService;

    private final AiWorkflowPersistenceService persistenceService;

    public AiWorkflowServiceImpl(
            GroqService groqService,

            AiWorkflowPersistenceService persistenceService) {

        this.groqService = groqService;

        this.persistenceService = persistenceService;
    }
    @Override
    public Long generateAndSaveWorkflow(
            String prompt) {
        try {

            String groqResponse =
                    groqService
                            .generateWorkflow(
                                    prompt);

            AiWorkflowResponse response =
                    extractWorkflowResponse(
                            groqResponse);

            return persistenceService
                    .saveWorkflow(
                            response);

        }
        catch (Exception e) {

            throw new RuntimeException(
                    e.getMessage());
        }
    }
    @Override
    public AiWorkflowResponse generateWorkflow(
            String prompt) {

        prompt = prompt.toLowerCase();

        AiWorkflowResponse response =
                new AiWorkflowResponse();

        response.setWorkflowName(
                "AI Generated Workflow");

        List<AiNodeDto> nodes =
                new ArrayList<>();

        List<AiEdgeDto> edges =
                new ArrayList<>();

        if (prompt.contains("devguard")) {
            nodes.add(
                    new AiNodeDto(
                            1L,
                            "DEVGUARD"));
        }

        if (prompt.contains("email")) {
            nodes.add(
                    new AiNodeDto(
                            2L,
                            "EMAIL"));
        }

        if (prompt.contains("webhook")) {
            nodes.add(
                    new AiNodeDto(
                            3L,
                            "WEBHOOK"));
        }

        if (prompt.contains("api")) {
            nodes.add(
                    new AiNodeDto(
                            4L,
                            "REST_API"));
        }

        if (prompt.contains("daily")
                || prompt.contains("every day")) {

            response.setSchedule("DAILY");

        } else if (prompt.contains("hourly")
                || prompt.contains("every hour")) {

            response.setSchedule("HOURLY");

        } else {

            response.setSchedule("MANUAL");
        }

        response.setNodes(nodes);
        for (int i = 0;
             i < nodes.size() - 1;
             i++) {

            edges.add(
                    new AiEdgeDto(
                            nodes.get(i).getId(),
                            nodes.get(i + 1).getId()));
        }
        response.setEdges(edges);
        return response;
    }
    private AiWorkflowResponse
    extractWorkflowResponse(
            String groqResponse)
            throws Exception {

        ObjectMapper objectMapper =
                new ObjectMapper();

        JsonNode root =
                objectMapper.readTree(
                        groqResponse);

        String workflowJson =
                root.path("choices")
                        .get(0)
                        .path("message")
                        .path("content")
                        .asText();

        return new ObjectMapper()
                .readValue(
                        workflowJson,
                        AiWorkflowResponse.class);
    }
}