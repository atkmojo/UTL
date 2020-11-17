package org.utl.core;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public interface TuningStrategyMethod<T> {
    Graph<Transition<Dataset<Row>>, DefaultEdge> tune(Graph<T, DefaultEdge> graph);
}
