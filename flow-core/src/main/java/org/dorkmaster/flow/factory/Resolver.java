package org.dorkmaster.flow.factory;

import java.util.Map;

public interface Resolver {
    Resolver add(Type type, String name, String clazz);

    Object create(Type type, String name);
}
