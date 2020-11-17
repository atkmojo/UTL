package org.utl.core.impl;
import org.apache.spark.sql.Row;
import org.utl.core.DatasetBuilder;


public class DatasetBuildersCatalogue {
    public static DatasetBuilder<Row> defaultDatasetBuilder = (rdd, structType, sparkSession) -> sparkSession.createDataFrame(rdd, structType);
}
