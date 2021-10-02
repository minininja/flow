package org.dorkmaster.flow.factory

import org.dorkmaster.flow.exception.DuplicateFlowException
import org.dorkmaster.flow.exception.FlowNotFoundException
import org.dorkmaster.flow.exception.MalformedYamlException
import org.dorkmaster.flow.exception.ResourceNotFoundException
import org.dorkmaster.flow.impl.Composite
import org.dorkmaster.flow.impl.Decider
import org.dorkmaster.flow.impl.Flow
import org.dorkmaster.flow.impl.Task
import org.dorkmaster.flow.impl.task.CompositeTask
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml

class StandAloneFactory implements FlowFactory {

    static Yaml parser = new Yaml()
    Logger logger = LoggerFactory.getLogger(this.getClass())
    Map flows = [:]
    Resolver resolver = new SimpleResolver() // default resolver

    @Override
    FlowFactory load(String resource) {
        def config = parser.load(readFile(resource))
        if (config.imports) {
            config.imports.each { imp ->
                load(imp)
            }
        }

        config["class"].each { type, items ->
            def t = Type.valueOf(type.toUpperCase())
            items.each { name, clz ->
                resolver.add(t, "${name}", clz)
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
            def flow = (Flow) resolver.create(Type.FLOW, definition.flow)
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
            return (Task) resolver.create(Type.TASK, definition)
        } else if (definition.composite) {
            def task = (CompositeTask) resolver.create(Type.TASK, definition.composite)
            definition.children.each { child ->
                task.addChild(constructTask(child))
            }
            return task
        } else if (definition.flow) {
            return (Task) constructFlowFromDefinition(definition)
        } else if (definition.task) {
            // this needs to be last otherwise it'll interfere with creating child flows
            return (Task) resolver.create(Type.TASK, definition.task)
        } else {
            throw new MalformedYamlException("could not create task '${definition}'")
        }
    }

    Decider constructDecider(definition) {
        logger.debug("decider: definition {}", definition)
        if (definition instanceof Map) {
            if (null != definition.composite) {
                def compositeDecider = (Composite) resolver.create(Type.DECIDER, definition.composite)
                definition.children.each { child ->
                    compositeDecider.addChild((Decider) constructDecider(child))
                }
                return compositeDecider
            } else if (null != definition.decider) {
                return (Decider) resolver.create(Type.DECIDER, "${definition.decider}")
            }
        } else {
            return (Decider) resolver.create(Type.DECIDER, "${definition}")
        }
        throw new MalformedYamlException("grr")
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
