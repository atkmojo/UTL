package org.utl.core;

import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public interface DatasetBuilder<T> {
    Dataset<T> buildDataset(RDD<T> rdd, StructType structType, SparkSession sparkSession);
}
