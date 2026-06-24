package com.example.flowforge.connector;

import org.springframework.stereotype.Component;

@Component
public class RestApiConnector
        implements Connector {

    @Override
    public String getType() {
        return "REST_API";
    }

    @Override
    public void execute() {

        System.out.println(
                "🌿 Executing REST API Connector");

    }
}