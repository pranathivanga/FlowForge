package com.example.flowforge.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkflowRequest {

    @NotBlank(message = "Workflow name is required")
    private String name;

    private String description;
}