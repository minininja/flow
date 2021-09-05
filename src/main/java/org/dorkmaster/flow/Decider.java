package org.dorkmaster.flow;

public interface Decider {
    boolean decide(FlowContext context);
}
