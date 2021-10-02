package org.dorkmaster.flow.factory

import org.dorkmaster.flow.exception.DuplicateClassException
import org.dorkmaster.flow.exception.MalformedYamlException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SimpleResolver implements Resolver {

    Logger logger= LoggerFactory.getLogger(this.getClass())
    Map clazzs = [:]

    SimpleResolver() {
        Type.values().each { t->
            clazzs."${t.searchType}" = [:]
        }
    }

    @Override
    Resolver add(Type type, String name, String clazz) {
        if (clazzs."${type.searchType}"."${name}") {
            throw new DuplicateClassException("${type} - ${name} - ${clazz}")
        }
        clazzs."${type.searchType}"."${name}"  = Class.forName(clazz).getConstructor(null)
        this
    }

    @Override
    Object create(Type type, String name) {
            logger.debug("construtor: {} {}", type, name)
            try {
                def clazz = clazzs."${type.searchType}"."${name}"
                return clazzs."${type.searchType}"."${name}".newInstance()
            } catch(NullPointerException e) {
                throw new MalformedYamlException("unable to construct ${type} ${name}", e)
            }
    }
}
