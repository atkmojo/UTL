package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.utl.core.GraphBuilder;
import org.utl.core.Transition;

import java.util.HashMap;
import java.util.List;

public class GenericGraphBuilder extends GraphBuilder<Transition<Dataset<Row>>> {
    @Override
    public void build(List<Transition<Dataset<Row>>> transitions) {
        Graph<Transition<Dataset<Row>>, DefaultEdge> dependencyGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        HashMap<String, Transition<Dataset<Row>>> transitionHashMap = new HashMap<>();

        transitions.forEach(rowTransition -> {
            dependencyGraph.addVertex(rowTransition);
            transitionHashMap.put(rowTransition.getName(), rowTransition);
        });

        transitions.forEach(transition -> {
            transition.getInputAttributes().forEach(inputAttribute -> {
                if (transitionHashMap.get(inputAttribute) != null)
                    dependencyGraph.addEdge(transitionHashMap.get(inputAttribute),  transition);
            });
        });
        this.dependencyGraph = dependencyGraph;
    }
}
