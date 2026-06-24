package com.example.flowforge.dto.response;

import com.example.flowforge.dto.AiEdgeDto;
import com.example.flowforge.dto.AiNodeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AiWorkflowResponse {

    private String workflowName;

    private List<AiNodeDto> nodes;

    private List<AiEdgeDto> edges;

    private String schedule;

}