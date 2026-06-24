package com.example.flowforge.controller;

import com.example.flowforge.connector.Connector;
import com.example.flowforge.connector.ConnectorRegistry;
import com.example.flowforge.service.ConnectorExecutionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/api/connectors")
public class ConnectorController {

    private final ConnectorRegistry connectorRegistry;
    private final ConnectorExecutionService
            connectorExecutionService;
    public ConnectorController(
            ConnectorRegistry connectorRegistry,
            ConnectorExecutionService connectorExecutionService) {

        this.connectorRegistry = connectorRegistry;
        this.connectorExecutionService =
                connectorExecutionService;
    }

    @PostMapping("/test")
    public String testConnector() {

        Connector connector =
                connectorRegistry
                        .getConnector("WEBHOOK");

        connector.execute();

        return "✅ Connector executed!";
    }
    @PostMapping("/rest")
    public String testRestConnector() {

        Connector connector =
                connectorRegistry
                        .getConnector("REST_API");

        connector.execute();

        return "✅ REST API Connector executed!";
    }
    @PostMapping("/email")
    public String testEmailConnector() {

        Connector connector =
                connectorRegistry
                        .getConnector("EMAIL");

        connector.execute();

        return "✅ Email Connector executed!";
    }
    @PostMapping("/devguard")
    public String testDevGuardConnector() {

        connectorExecutionService
                .execute("DEVGUARD");



        return "✅ DevGuard Connector executed!";
    }
    @PostMapping("/execute/{type}")
    public String executeConnector(
            @PathVariable String type) {

        connectorExecutionService
                .execute(type);

        return "✅ Executed: " + type;
    }
}