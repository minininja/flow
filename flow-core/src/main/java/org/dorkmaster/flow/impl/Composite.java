package org.dorkmaster.flow.impl;

public interface Composite<T> {
    Composite addChild(T item);
}
