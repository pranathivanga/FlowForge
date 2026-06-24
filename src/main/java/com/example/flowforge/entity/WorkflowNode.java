package com.example.flowforge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "workflow_nodes")
public class WorkflowNode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(length = 1000)
    private String configuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id", nullable = false)
    @JsonIgnore
    private Workflow workflow;

    @OneToMany(mappedBy = "sourceNode")
    @JsonIgnore
    private List<WorkflowEdge> outgoingEdges = new ArrayList<>();

    @OneToMany(mappedBy = "targetNode")
    @JsonIgnore
    private List<WorkflowEdge> incomingEdges = new ArrayList<>();

    @Getter
    @Setter
    @Column(nullable = false)
    private String connectorType;
}