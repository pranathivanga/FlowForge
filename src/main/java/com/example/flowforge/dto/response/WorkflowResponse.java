package com.example.flowforge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkflowResponse {

    private Long id;
    private String name;
    private String description;
    private boolean enabled;
    private Integer version;
}