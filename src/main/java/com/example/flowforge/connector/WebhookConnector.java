package com.example.flowforge.connector;

import org.springframework.stereotype.Component;

@Component
public class WebhookConnector
        implements Connector {

    @Override
    public String getType() {
        return "WEBHOOK";
    }

    @Override
    public void execute() {

        System.out.println(
                "🌿 Executing Webhook Connector");

    }
}