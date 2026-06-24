package com.example.flowforge.connector;

import org.springframework.stereotype.Component;

@Component
public class DevGuardConnector
        implements Connector {

    @Override
    public String getType() {
        return "DEVGUARD";
    }

    @Override
    public void execute() {

        System.out.println(
                "🛡️ Executing DevGuard Scan");

    }
}