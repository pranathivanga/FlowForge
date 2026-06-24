package com.example.flowforge.connector;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConnectorRegistry {

    private final Map<String, Connector> connectors =
            new HashMap<>();

    public ConnectorRegistry(
            List<Connector> connectorList) {

        for (Connector connector : connectorList) {
            connectors.put(
                    connector.getType(),
                    connector);
        }
    }

    public Connector getConnector(
            String type) {

        return connectors.get(type);
    }
}