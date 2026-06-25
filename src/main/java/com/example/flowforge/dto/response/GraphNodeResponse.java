package com.example.flowforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GraphNodeResponse {

    private Long id;

    private String name;

    private String type;

    private String connectorType;
}