package com.example.flowforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GraphEdgeResponse {

    private Long source;

    private Long target;
}