package org.utl.core.impl;

import org.apache.spark.sql.Row;
import org.utl.core.Transition;

import java.util.List;

public class Stage {
    private String name;
    private String type;
    private List<SqlTransition> transitions;

    public Stage(String name, String type, List<SqlTransition> transitions) {
        this.name = name;
        this.type = type;
        this.transitions = transitions;
    }

    public List<SqlTransition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<SqlTransition> transitions) {
        this.transitions = transitions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
