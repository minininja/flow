package org.dorkmaster.flow;

/**
 * A marker for composite items, could be a Decider or a Task.
 * @param <T>
 */
public interface Composite<T> {
    Composite addChild(T item);
}
