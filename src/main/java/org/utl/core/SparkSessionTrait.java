package org.utl.core;

import org.apache.spark.sql.SparkSession;

public interface SparkSessionTrait {
    SparkSession spark = SparkSession.builder()
            .config("spark.master", "local[*]")
            .config("spark.executor.cores", "2")
            .config("spark.driver.cores", "2")
            .config("spark.driver.memory", "4")
            .config("hive.exec.scratchdir", "/tmp")
            .config("log4j.rootCategory", "ERROR")
            .getOrCreate();
}
