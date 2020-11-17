package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.utl.core.Transition;

public class DefaultTransition extends Transition<Dataset<Row>> {
    public DefaultTransition(String name) {
        super(name);
    }

    public Dataset<Dataset<Row>> perform(Dataset<Dataset<Row>> dataset) {
        return null;
    }
}
