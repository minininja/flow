package org.dorkmaster.flow;

/**
 * Defines a simple key value pairing for storing the "execution context" of a flow.
 */
public interface FlowContext {
    FlowContext set(String name, Object value);
    Object get(String name);
}
