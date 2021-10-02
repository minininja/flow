package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;

public class OrDecider extends CompositeDecider {
    @Override
    public boolean decide(FlowContext context) {
        super.decide(context);

        boolean result = false;
        for (Decider decider : deciders) {
            result = result | decider.decide(context);
        }
        return result;
    }
}
