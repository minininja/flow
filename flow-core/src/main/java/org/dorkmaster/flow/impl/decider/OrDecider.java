package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class OrDecider extends CompositeDecider {
    @Override
    public boolean decide(FlowContext context) {
        validate();

        boolean result = false;
        for (Decider decider : deciders) {
            result = result | decider.decide(context);
        }
        return result;
    }
}
