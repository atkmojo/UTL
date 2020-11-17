package org.utl.core.impl;

import org.apache.spark.sql.types.StructField;
import org.utl.core.StructFieldEnhancer;

public class StandardizationCatalogue {
    public static StructFieldEnhancer<StructField> changeToUpperCase = (field) -> new StructField(field.name().toUpperCase(), field.dataType(), field.nullable(), field.metadata());
    public static StructFieldEnhancer<StructField> changeToLowerCase = (field) -> new StructField(field.name().toLowerCase(), field.dataType(), field.nullable(), field.metadata());
}
