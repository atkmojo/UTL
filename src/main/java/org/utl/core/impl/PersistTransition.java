package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.storage.StorageLevel;
import org.utl.core.Transition;

public class PersistTransition extends Transition<Dataset<Row>> {
    private final StorageLevel storageLevel;
    public PersistTransition(StorageLevel storageLevel) {
        super("persist");
        this.storageLevel = storageLevel;
    }

    @Override
    public Dataset<Row> perform(Dataset<Row> dataset) {
        return dataset.persist(this.storageLevel);
    }

    public String toString() {
        return "persist: " + this.storageLevel;
    }
}
