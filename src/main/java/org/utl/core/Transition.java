package org.utl.core;
import org.apache.spark.sql.Dataset;

import java.util.List;

abstract public class Transition<T> {
    protected final String name;
    protected Boolean isActivated;
    protected List<String> inputAttributes;

    protected Transition(String name) {
        this.name = name;
    }


    public T perform(T dataset) {
        return dataset;
    }

    public String getName() { return this.name; }

    public List<String> getInputAttributes() {
        return inputAttributes;
    }
}
