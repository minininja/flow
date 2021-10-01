package org.dorkmaster.flow.factory

import org.dorkmaster.flow.Composite
import org.dorkmaster.flow.Decider
import org.dorkmaster.flow.Flow
import org.dorkmaster.flow.Task
import org.dorkmaster.flow.exception.DuplicateClassException
import org.dorkmaster.flow.exception.DuplicateFlowException
import org.dorkmaster.flow.exception.FlowNotFoundException
import org.dorkmaster.flow.exception.MalformedYamlException
import org.dorkmaster.flow.exception.ResourceNotFoundException
import org.dorkmaster.flow.impl.task.CompositeTask
import org.yaml.snakeyaml.Yaml
import org.slf4j.*

class Factory implements FlowFactory {

    static Yaml parser = new Yaml()
    Logger logger = LoggerFactory.getLogger(this.getClass())
    Map clazzs = [flow: [:], decider: [:], task: [:]]
    Map flows = [:]

    @Override
    FlowFactory load(String resource) {
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
        def definition = flows."${name}"
        logger.debug("root: definition: {}", definition)
        if (definition) {
            return constructFlowFromDefinition(definition)
        } else {
            throw new FlowNotFoundException("Flow '${name}' does not exist")
        }
    }

    Flow constructFlowFromDefinition(definition) {
        logger.debug("flow: definition {}", definition)
        if (definition.flow) {
            def flow = (Flow) constructor("flow", definition.flow)
            def decider = constructDecider(definition.decider)
            def task = constructTask(definition.task)
            return flow.setDecider(decider).setTask(task)
        } else {
            throw new MalformedYamlException("could not create flow '${definition}'")
        }
    }

    Task constructTask(definition) {
        logger.debug("task: definition {}", definition)
        if (definition instanceof String)   {
            return (Task) constructor("task", definition)
        } else if (definition.composite) {
            def task = (CompositeTask) constructor("task", definition.composite)
            definition.children.each { child ->
                task.addChild(constructTask(child))
            }
            return task
        } else if (definition.flow) {
            return (Task) constructFlowFromDefinition(definition)
        } else if (definition.task) {
            // this needs to be last otherwise it'll interfere with creating child flows
            return (Task) constructor("task", definition.task)
        } else {
            throw new MalformedYamlException("could not create task '${definition}'")
        }
    }

    Decider constructDecider(definition) {
        logger.debug("decider: definition {}", definition)
        if (definition instanceof Map) {
            if (definition.composite) {
                def compositeDecider = (Composite) constructor("decider", definition.composite)
                definition.children.each { child ->
                    compositeDecider.addChild((Decider) constructDecider(child))
                }
                return compositeDecider
            } else if (definition.decider) {
                return (Decider) constructor("decider", definition.decider)
            }
        } else {
            return (Decider) constructor("decider", definition)
        }
        throw new MalformedYamlException("grr")
    }

    def constructor(type, definition) {
        logger.debug("construtor: {} {}", type, definition)
        try {
            def clazz = clazzs."${type}"."${definition}"
            return clazzs."${type}"."${definition}".newInstance()
        } catch(NullPointerException e) {
            throw new MalformedYamlException("unable to construct ${definition}", e)
        }
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
