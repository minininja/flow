package org.dorkmaster.flow;

/**
 * This is the conditional part of an if/then contract
 */
public interface Decider {
    boolean decide(FlowContext context);
}
