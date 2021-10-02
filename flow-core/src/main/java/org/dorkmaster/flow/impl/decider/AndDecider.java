package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;

public class AndDecider extends CompositeDecider {
    @Override
    public boolean decide(FlowContext context) {
        super.decide(context);

        for (Decider decider : deciders) {
            if (!decider.decide(context)) {
                return false;
            }
        }

        return true;
    }
}
