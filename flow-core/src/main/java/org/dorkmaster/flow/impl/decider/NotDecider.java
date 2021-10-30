package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.ConfigurationException;

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
        validate(1);
        return !deciders.get(0).decide(context);
    }
}
