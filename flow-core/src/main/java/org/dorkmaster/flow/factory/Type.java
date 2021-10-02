package org.dorkmaster.flow.factory;

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
