package org.dorkmaster.flow.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

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
