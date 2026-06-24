package com.example.flowforge.service.impl;

import com.example.flowforge.service.GroqService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GroqServiceImpl
        implements GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate =
            new RestTemplate();

    @Override
    public String generateWorkflow(
            String prompt) {

        String url =
                "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers =
                new HttpHeaders();

        headers.setBearerAuth(apiKey);

        headers.setContentType(
                MediaType.APPLICATION_JSON);

        String systemPrompt = """
You are a workflow generation engine.

Return ONLY valid JSON.

Format:

{
  "workflowName":"string",
  "schedule":"DAILY",
  "nodes":[
    {
      "id":1,
      "type":"DEVGUARD"
    }
  ],
  "edges":[
    {
      "source":1,
      "target":2
    }
  ]
}

Supported node types:
DEVGUARD
EMAIL
WEBHOOK
REST_API

No markdown.
No explanation.
Only JSON.
""";

        Map<String, Object> body =
                Map.of(
                        "model",
                        "llama-3.3-70b-versatile",
                        "messages",
                        new Object[]{
                                Map.of(
                                        "role",
                                        "system",
                                        "content",
                                        systemPrompt
                                ),
                                Map.of(
                                        "role",
                                        "user",
                                        "content",
                                        prompt
                                )
                        }
                );

        HttpEntity<Map<String, Object>>
                entity =
                new HttpEntity<>(
                        body,
                        headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        String.class);

        return response.getBody();
    }
}