package org.dorkmaster.flow.impl;

import org.dorkmaster.flow.FlowContext;

import java.util.HashMap;
import java.util.Map;

public class MapFlowContext implements FlowContext {
    protected Map<String, Object> context = new HashMap<>();

    public MapFlowContext set(String name, Object value) {
        context.put(name, value);
        return this;
    }

    public Object get(String name) {
        return context.get(name);
    }
}
