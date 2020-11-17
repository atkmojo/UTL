package org.utl.core;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public abstract class GraphBuilder<T> {
    protected Graph<T, DefaultEdge> dependencyGraph;
    public void build(List<T> transitions) {  };

    public Graph<T, DefaultEdge> getGraph() { return this.dependencyGraph; }
}
