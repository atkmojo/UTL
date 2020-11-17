package org.utl.core.impl;

import org.apache.spark.sql.catalog.Column;

import java.util.List;

public class Workflow {
    private final List<DataSource> inputs;
    private final List<Stage> stages;

    public Workflow(List<DataSource> inputs, List<Stage> stages) {
        this.inputs = inputs;
        this.stages = stages;
    }


    public List<DataSource> getSources() {
        return inputs;
    }

    public List<Stage> getStages() {
        return stages;
    }
}
