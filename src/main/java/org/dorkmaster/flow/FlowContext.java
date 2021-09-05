package org.dorkmaster.flow;

import java.util.HashMap;
import java.util.Map;

public class FlowContext {
    protected Map<String, Object> context = new HashMap<>();

    public FlowContext set(String name, Object value) {
        context.put(name, value);
        return this;
    }

    public Object get(String name) {
        return context.get(name);
    }
}
