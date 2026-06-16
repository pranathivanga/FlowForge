package com.example.flowforge.dag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DagGraph {

    private final Map<Long, List<Long>> adjacencyList =
            new HashMap<>();

    public void addNode(Long nodeId) {
        adjacencyList.putIfAbsent(nodeId, new ArrayList<>());
    }

    public void addEdge(Long sourceNodeId, Long targetNodeId) {
        adjacencyList
                .computeIfAbsent(
                        sourceNodeId,
                        k -> new ArrayList<>())
                .add(targetNodeId);

        adjacencyList.putIfAbsent(
                targetNodeId,
                new ArrayList<>());
    }

    public Map<Long, List<Long>> getAdjacencyList() {
        return adjacencyList;
    }
}