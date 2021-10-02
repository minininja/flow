package org.dorkmaster.flow.factory;

import org.dorkmaster.flow.impl.Flow;

public interface FlowFactory {

    FlowFactory load(String resource);
    Flow constructFlow(String name);

}
