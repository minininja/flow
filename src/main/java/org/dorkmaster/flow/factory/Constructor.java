package org.dorkmaster.flow.factory;

public class Constructor {
    java.lang.reflect.Constructor c;

    public Constructor(String className) {
        try {
            Class clz = Class.forName(className);
            c = clz.getConstructors()[0];
        } catch (ClassNotFoundException e) {

        }
    }
}
