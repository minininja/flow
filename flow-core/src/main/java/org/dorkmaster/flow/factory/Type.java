package org.dorkmaster.flow.factory;

import org.dorkmaster.flow.Task;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    FLOW("flow"), DECIDER("decider"), TASK("task");

    private String searchType;

    Type(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }
}
