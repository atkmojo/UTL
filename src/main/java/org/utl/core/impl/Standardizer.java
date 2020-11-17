package org.utl.core.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.utl.core.StructFieldEnhancer;
import org.utl.core.SparkSessionTrait;

import java.util.Arrays;

public class Standardizer implements SparkSessionTrait {
    public static StructType standardize(Dataset<Row> ds, StructFieldEnhancer<StructField> standardizationFunction) {
        return new StructType(Arrays.stream(ds.schema().fields()).map(standardizationFunction::stantardize).toArray(StructField[]::new));
    }
}
