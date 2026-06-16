package com.example.flowforge.controller;

import com.example.flowforge.dag.DagGraph;
import com.example.flowforge.dag.DagValidator;
import com.example.flowforge.dag.TopologicalSorter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DagTestController {

    private final DagValidator dagValidator;
    private final TopologicalSorter topologicalSorter;

    public DagTestController(DagValidator dagValidator, TopologicalSorter topologicalSorter) {
        this.dagValidator = dagValidator;
        this.topologicalSorter = topologicalSorter;
    }

    @GetMapping("/api/dag/test")
    public String testDag() {

        DagGraph graph = new DagGraph();

        graph.addNode(1L);
        graph.addNode(2L);
        graph.addNode(3L);

        graph.addEdge(1L, 2L);
        graph.addEdge(2L, 3L);
        graph.addEdge(3L, 1L);
        boolean hasCycle = dagValidator.hasCycle(graph);

        return hasCycle
                ? "❌ Cycle detected!"
                : "✅ Valid DAG!";
    }
    @GetMapping("/api/dag/order")
    public Object getExecutionOrder() {

        DagGraph graph = new DagGraph();

        graph.addNode(1L);
        graph.addNode(2L);
        graph.addNode(3L);
        graph.addNode(4L);

        graph.addEdge(1L, 2L);
        graph.addEdge(2L, 3L);


        if (dagValidator.hasCycle(graph)) {
            return "❌ Invalid workflow: cycle detected!";
        }

        return topologicalSorter.sort(graph);
    }
}