package com.example.flowforge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionMessage {

//    private static final long serialVersionUID = 1L;

    private Long executionId;

    private Long workflowId;
}