package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.utl.core.Transition;
import org.utl.core.TuningStrategyMethod;
import java.util.ArrayList;
import java.util.Iterator;

public class TuningStrategy {
    public static TuningStrategyMethod<SqlTransition> repartitionWhenWindowing = (graph -> {
        Graph<Transition<Dataset<Row>>, DefaultEdge> optimizedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);


        graph.vertexSet().forEach(vertex -> {
            RepartitionTransition repartitionTransition = new RepartitionTransition(vertex.getPartitioningKeys());
            optimizedGraph.addVertex(repartitionTransition);
            optimizedGraph.addVertex(vertex);
            optimizedGraph.addEdge(repartitionTransition, vertex);
        });

        graph.edgeSet().forEach(edge -> {
            optimizedGraph.addEdge(graph.getEdgeSource(edge), graph.getEdgeTarget(edge));
        });
        return optimizedGraph;
    });
    public TuningStrategyMethod<SqlTransition> broadcastSmallTables = (graph -> new DefaultDirectedGraph<Transition<Dataset<Row>>, DefaultEdge>(DefaultEdge.class));
}