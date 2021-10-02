package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.exception.ConfigurationException;
import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;

public class NotDecider extends CompositeDecider implements Decider {

    @Override
    public CompositeDecider addChild(Decider decider) {
        if (deciders.size() != 0) {
            throw new ConfigurationException("Only one child is allowed");
        }
        super.addChild(decider);
        return this;
    }

    @Override
    public boolean decide(FlowContext context) {
        if (0 == deciders.size()) {
            throw new NoDecidersException();
        }

        return !deciders.get(0).decide(context);
    }
}
