package org.dorkmaster.flow.factory;

public interface Resolver {
    Resolver add(Type type, String name, String clazz);

    Object create(Type type, String name);
}
