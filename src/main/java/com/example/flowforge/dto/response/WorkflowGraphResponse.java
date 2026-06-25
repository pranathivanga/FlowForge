package com.example.flowforge.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkflowGraphResponse {

    private Long workflowId;

    private String workflowName;

    private List<GraphNodeResponse> nodes;

    private List<GraphEdgeResponse> edges;
}