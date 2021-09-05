package org.dorkmaster.flow.factory;

import org.dorkmaster.flow.Flow;

public interface FlowFactory {

    Factory load(String resource);
    Flow constructFlow(String name);

}
