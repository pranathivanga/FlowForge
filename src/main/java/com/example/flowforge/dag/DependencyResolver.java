package com.example.flowforge.dag;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DependencyResolver {

    public boolean canExecute(
            Long nodeId,
            Map<Long, List<Long>> reverseAdjacencyList,
            Set<Long> completedNodes) {

        List<Long> dependencies =
                reverseAdjacencyList.getOrDefault(
                        nodeId,
                        List.of());

        return completedNodes.containsAll(dependencies);
    }
}