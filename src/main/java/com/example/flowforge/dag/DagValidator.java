package com.example.flowforge.dag;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DagValidator {

    public boolean hasCycle(DagGraph graph) {

        Set<Long> visited = new HashSet<>();
        Set<Long> recursionStack = new HashSet<>();

        for (Long nodeId : graph.getAdjacencyList().keySet()) {
            if (dfs(nodeId, graph.getAdjacencyList(),
                    visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean dfs(
            Long currentNode,
            Map<Long, List<Long>> adjacencyList,
            Set<Long> visited,
            Set<Long> recursionStack) {

        if (recursionStack.contains(currentNode)) {
            return true;
        }

        if (visited.contains(currentNode)) {
            return false;
        }

        visited.add(currentNode);
        recursionStack.add(currentNode);

        for (Long neighbor :
                adjacencyList.getOrDefault(currentNode, List.of())) {

            if (dfs(neighbor,
                    adjacencyList,
                    visited,
                    recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(currentNode);

        return false;
    }
}