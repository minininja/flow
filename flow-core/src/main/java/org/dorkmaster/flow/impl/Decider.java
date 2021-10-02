package org.dorkmaster.flow.impl;

public interface Decider {
    boolean decide(FlowContext context);
}
