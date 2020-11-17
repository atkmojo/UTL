package org.utl;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.utl.core.SparkSessionTrait;
import org.utl.core.Transition;
import org.utl.core.impl.*;
import org.utl.util.JsonHandler;
import java.io.FileNotFoundException;
import java.util.*;

public class App implements SparkSessionTrait {

    public static void main(String[] args) throws FileNotFoundException {
        String PATH = "/home/faris/IdeaProjects/lab/src/main/resources";
        Map<String, String> sparkOptions = new HashMap<>();

        sparkOptions.put("inferSchema", "true");
        sparkOptions.put("header", "true");

        // Charge the dataset
        Dataset<Row> df1 = spark.read().options(sparkOptions).csv(PATH + "/sample_housing.csv");

        // Load Workflow from json
        JsonHandler<Workflow> jsonHandler = new JsonHandler<>(Workflow.class);
        Workflow workflow = jsonHandler
                .jsonFromFile("src/main/resources/workflows/workflow.json");

        Utl.runPipline(workflow).show();
    }
}
