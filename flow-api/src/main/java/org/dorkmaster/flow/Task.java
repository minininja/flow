package org.dorkmaster.flow;

/**
 * This is the "then" of an if/then construct.
 */
public interface Task {
    FlowContext execute(FlowContext context);
}
