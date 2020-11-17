package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.utl.core.GraphBuilder;
import org.utl.core.Transition;
import org.utl.core.TuningStrategyMethod;

import java.util.List;

public class GraphOptimizer {

    public static Graph<Transition<Dataset<Row>>, DefaultEdge> optimize(TuningStrategyMethod<SqlTransition> tuningStrategyMethod, Graph<SqlTransition, DefaultEdge> graph) {
        return tuningStrategyMethod.tune(graph);
    }
}
