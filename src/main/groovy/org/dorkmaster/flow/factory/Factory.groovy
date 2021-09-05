package org.dorkmaster.flow.factory

import org.dorkmaster.flow.Flow
import org.dorkmaster.flow.exception.DuplicateFlowException
import org.dorkmaster.flow.exception.ResourceNotFoundException
import org.yaml.snakeyaml.Yaml

class Factory implements FlowFactory {

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

    def config

    @Override
    Factory load(String resource) {
        Yaml parser = new Yaml()
        config = parser.load(readFile(resource))

        if (!config["class"].flow)
            config["class"].flow = [:]
        if (!config["class"].flow)
            config["class"].decider = [:]
        if (!config["class"].flow)
            config["class"].task = [:]

        processImports(config)

        return this
    }


    @Override
    Flow constructFlow(String name) {

    }

    def build(String type, String name) {
        def clzName = config["class"]["${type}"]["${name}"]
        def clz = Class.forName(clzName)
        clz.getConstructors()[0].newInstance(null)
    }

    def processImports(config) {
        if (config.imports) {
            config.imports.each { imp ->
                def impConfig = load(imp).config
                // recurse
                if (impConfig.imports) {
                    impConfig = processImports(impConfig)
                }

                // add the classes
                impConfig["class"].decider.each { clz ->
                    config["class"].decider << clz
                }
                impConfig["class"].task.each { clz ->
                    config["class"].task << clz
                }
                impConfig["class"].flow.each { clz ->
                    config["class"].flow << clz
                }

                impConfig.flow.each { flow ->
                    if (config.flow['${flow}']) {
                        throw new DuplicateFlowException(flow)
                    }
                }
            }
        }
    }
}
