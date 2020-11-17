package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.utl.core.SparkSessionTrait;
import org.utl.core.Transition;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Utl implements SparkSessionTrait {

    public static Dataset<Row> runPipline(Workflow workflow) {
        
        // Load dataset
        Map<String, String> sparkOptions = new HashMap<>();

        sparkOptions.put("inferSchema", "true");
        sparkOptions.put("header", "true");

        Dataset<Row> dataset = spark.read().options(sparkOptions).csv(workflow.getSources().get(0).getPath());

        // Run Stardization
        StructType standardizedSchema = Standardizer.standardize(dataset, StandardizationCatalogue.changeToUpperCase);
        dataset = DatasetBuildersCatalogue.defaultDatasetBuilder
                .buildDataset(dataset.rdd(), standardizedSchema, spark);

        //Graph Builder
        SqlGraphBuilder sqlGraphBuilder = new SqlGraphBuilder();
        sqlGraphBuilder.build(workflow.getStages().get(1).getTransitions());
        Graph<SqlTransition, DefaultEdge> graph = sqlGraphBuilder.getDependencyGraph();

        //Optimizer
        Graph<Transition<Dataset<Row>>, DefaultEdge> optimizedGraph = GraphOptimizer
                .optimize(TuningStrategy.repartitionWhenWindowing, graph);

        //Traverse graph and run processing
        return traverseGraph(dataset, optimizedGraph);
    }
    public static Dataset<Row> traverseGraph(Dataset<Row> dataset, Graph<Transition<Dataset<Row>>, DefaultEdge> graph)
    {
        Iterator<Transition<Dataset<Row>>> iterator = new TopologicalOrderIterator<>(graph);
        while (iterator.hasNext()) {
            Transition<Dataset<Row>> transition = iterator.next();
            System.out.println("perform: " + transition);
            dataset = transition.perform(dataset);
        }
        return dataset;
    }

}
