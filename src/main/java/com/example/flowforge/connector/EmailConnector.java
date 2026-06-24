package com.example.flowforge.connector;

import org.springframework.stereotype.Component;

@Component
public class EmailConnector
        implements Connector {

    @Override
    public String getType() {
        return "EMAIL";
    }

    @Override
    public void execute() {

        System.out.println(
                "📧 Executing Email Connector");

    }
}