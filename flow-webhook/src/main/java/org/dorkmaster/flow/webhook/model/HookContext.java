package org.dorkmaster.flow.webhook.model;

import org.dorkmaster.flow.impl.FlowContext;

import java.util.Map;

public class HookContext extends FlowContext {
    public Map getContext() {
        return super.context;
    }

    public HookContext setContext(Map context) {
        super.context =  context;
        return this;
    }
}
