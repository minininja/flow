package org.dorkmaster.flow.common.decider;

import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;

public class FalseDecider implements Decider {
    public boolean decide(FlowContext context) {
        return false;
    }
}
