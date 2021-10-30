package org.dorkmaster.flow.webhook.model;

import org.dorkmaster.flow.impl.MapFlowContext;

import java.util.Map;

public class HookContext extends MapFlowContext {
    public Map getContext() {
        return super.context;
    }

    public HookContext setContext(Map context) {
        super.context =  context;
        return this;
    }
}
