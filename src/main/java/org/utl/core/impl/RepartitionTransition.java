package org.utl.core.impl;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.utl.core.Transition;

import java.util.ArrayList;
import java.util.List;

public class RepartitionTransition extends Transition<Dataset<Row>> {


    public RepartitionTransition(List<String> columnsName) {
        super("repartition");
        this.inputAttributes = new ArrayList<String>();
        columnsName.forEach(column -> {
            this.inputAttributes.add(column);
        });
    }

    @Override
    public Dataset<Row> perform(Dataset<Row> dataset) {
        return dataset.repartition(new Column(inputAttributes.get(0)));
    }

    public String toString() {
        return "repartitioning: " + this.inputAttributes;
    }
}
