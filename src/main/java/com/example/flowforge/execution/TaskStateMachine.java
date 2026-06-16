package com.example.flowforge.execution;

import com.example.flowforge.entity.ExecutionStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskStateMachine {

    public boolean canTransition(
            ExecutionStatus current,
            ExecutionStatus next) {

        return switch (current) {
            case PENDING ->
                    next == ExecutionStatus.RUNNING;

            case RUNNING ->
                    next == ExecutionStatus.SUCCESS
                            || next == ExecutionStatus.FAILED
                            || next == ExecutionStatus.CANCELLED;

            case SUCCESS, FAILED, CANCELLED ->
                    false;
        };
    }
}