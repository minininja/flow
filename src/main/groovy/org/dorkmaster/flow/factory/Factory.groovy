package org.dorkmaster.flow.factory


import org.dorkmaster.flow.Decider
import org.dorkmaster.flow.Flow
import org.dorkmaster.flow.Task
import org.dorkmaster.flow.exception.DuplicateClassException
import org.dorkmaster.flow.exception.DuplicateFlowException
import org.dorkmaster.flow.exception.ResourceNotFoundException
import org.yaml.snakeyaml.Yaml

class Factory implements FlowFactory {

    static Yaml parser = new Yaml()
    def clazzs = [flow:[:], decider: [:], task: [:]]
    def flows = [:]

    @Override
    Factory load(String resource) {
        def config = parser.load(readFile(resource))

        if (config.imports) {
            config.imports.each { imp ->
                load(imp)
            }
        }

        config["class"].each { type, items ->
            items.each { name, clz ->
                if (clazzs?."${type}"?."${name}") {
                    throw new DuplicateClassException("${type} - ${name} - ${clz}")
                }
                clazzs."${type}"."${name}" = Class.forName(clz).getConstructor(null)
            }
        }

        config?.flows?.each { name, definition ->
            if (flows."${name}") {
                throw new DuplicateFlowException(name)
            }
            flows."${name}" = definition
        }

        return this
    }

    @Override
    Flow constructFlow(String name) {
        def defintion = flows."${name}"
        def flow = (Flow) clazzs.flow."${defintion.flow}".newInstance()
        def decider = (Decider) constructDecider(defintion)
        def task = (Task) constructTask(defintion)
        flow.setDecider(decider).setTask(task)
    }

    def constructDecider(definition) {
        constructor("decider", definition)
    }

    def constructTask(definition) {
        constructor("task", definition)
    }

    def constructor(type, definition) {
        def d = definition."${type}"
        def clazz = clazzs."${type}"."${d}"
        // simple class
        if (clazz) {
            clazz = clazzs."${type}"."${d}".newInstance()
        }
        // not a simple class, but a composite
        else {
            clazz = clazzs."${type}"
            clazz = clazz."${d.composite}"
            clazz = clazz.newInstance()
            d.children.each { child ->
                clazz.addChild(constructDecider(child))
            }
        }
        clazz
    }

    def readFile(resource) {
        def res = this.getClass().getResourceAsStream("/${resource.replaceAll(/\./, "/")}.yaml")
        if (res) {
            StringBuilder sb = new StringBuilder()
            res.readLines().each { line ->
                sb.append(line).append("\n")
            }
            return sb.toString()
        }
        throw new ResourceNotFoundException(resource)
    }
}
