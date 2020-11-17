package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
import org.utl.core.Transition;

import java.util.List;
public class SqlTransition extends Transition<Dataset<Row>> {

    private final String type;
    private final String targetAttribute;
    private List<String> partitioningKeys;
    private final String expression;

    public SqlTransition(String targetColumn, List<String> inputColumns, List<String> partitioningKeys, String type, String expression) {
        super(targetColumn);
        this.type = type;
        this.targetAttribute = targetColumn;
        this.inputAttributes = inputColumns;
        this.partitioningKeys = partitioningKeys;
        this.expression = expression;
    }

    public SqlTransition(String expression, String targetColumn, String type) {
        super(targetColumn);
        this.expression = expression;
        this.targetAttribute = targetColumn;
        this.type = type;
    }

    @Override
    public Dataset<Row> perform(Dataset<Row> dataset) {
        return dataset.withColumn(this.targetAttribute.toString(), functions.expr(this.expression));
    }

    public String toString() {
        return "processing: " + this.targetAttribute + " using " + this.expression;
    }

    public List<String> getInputAttributes() {
        return inputAttributes;
    }

    public void setInputAttributes(List<String> inputAttributes) {
        this.inputAttributes = inputAttributes;
    }

    public List<String> getPartitioningKeys() {
        return partitioningKeys;
    }

    public void setPartitioningKeys(List<String> partitioningKeys) {
        this.partitioningKeys = partitioningKeys;
    }
    public String getTargetAttribute() { return this.targetAttribute; }

}
