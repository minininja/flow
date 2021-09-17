package org.dorkmaster.flow;

public interface Composite<T> {
    Composite addChild(T item);
}
