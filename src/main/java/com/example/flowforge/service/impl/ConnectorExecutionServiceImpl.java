package com.example.flowforge.service.impl;

import com.example.flowforge.connector.Connector;
import com.example.flowforge.connector.ConnectorRegistry;
import com.example.flowforge.service.ConnectorExecutionService;
import org.springframework.stereotype.Service;

@Service
public class ConnectorExecutionServiceImpl
        implements ConnectorExecutionService {

    private final ConnectorRegistry connectorRegistry;

    public ConnectorExecutionServiceImpl(
            ConnectorRegistry connectorRegistry) {
        this.connectorRegistry = connectorRegistry;
    }

    @Override
    public void execute(String connectorType) {

        Connector connector =
                connectorRegistry
                        .getConnector(connectorType);

        if (connector == null) {
            throw new RuntimeException(
                    "Connector not found: "
                            + connectorType);
        }

        connector.execute();
    }
}