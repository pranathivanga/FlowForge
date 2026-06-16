package com.example.flowforge.dag;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TopologicalSorter {

    public List<Long> sort(DagGraph graph) {

        Set<Long> visited = new HashSet<>();
        Stack<Long> stack = new Stack<>();

        for (Long nodeId : graph.getAdjacencyList().keySet()) {
            if (!visited.contains(nodeId)) {
                dfs(nodeId,
                        graph.getAdjacencyList(),
                        visited,
                        stack);
            }
        }

        List<Long> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private void dfs(
            Long currentNode,
            Map<Long, List<Long>> adjacencyList,
            Set<Long> visited,
            Stack<Long> stack) {

        visited.add(currentNode);

        for (Long neighbor :
                adjacencyList.getOrDefault(currentNode, List.of())) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor,
                        adjacencyList,
                        visited,
                        stack);
            }
        }

        stack.push(currentNode);
    }
}