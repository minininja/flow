package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class AndDecider extends CompositeDecider {
    @Override
    public boolean decide(FlowContext context) {
        validate();

        for (Decider decider : deciders) {
            if (!decider.decide(context)) {
                return false;
            }
        }

        return true;
    }
}
