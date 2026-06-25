package com.example.flowforge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardResponse {

    private Long totalWorkflows;

    private Long activeWorkflows;

    private Long totalNodes;

    private Long totalExecutions;

    private Long disabledWorkflows;
}