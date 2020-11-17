package org.utl.core.impl;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.utl.core.GraphBuilder;
import org.utl.core.TuningStrategyMethod;

import java.util.HashMap;
import java.util.List;

public class SqlGraphBuilder extends GraphBuilder<SqlTransition> {
    public void build(List<SqlTransition> transitions) {
        Graph<SqlTransition, DefaultEdge> dependencyGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
        HashMap<String, SqlTransition> transitionHashMap = new HashMap<>();

        transitions.forEach(rowTransition -> {
            dependencyGraph.addVertex(rowTransition);
            transitionHashMap.put(rowTransition.getTargetAttribute(), rowTransition);
        });
        transitions.forEach(sqlTransition -> {
            sqlTransition.getInputAttributes().forEach(inputAttribute -> {
                if (transitionHashMap.get(inputAttribute) != null)
                    dependencyGraph.addEdge(transitionHashMap.get(inputAttribute),  sqlTransition);
            });
        });
        this.dependencyGraph = dependencyGraph;
    }

    public Graph<SqlTransition, DefaultEdge> getDependencyGraph() { return this.dependencyGraph; }

}
