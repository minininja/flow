package org.dorkmaster.flow.common.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class FalseDecider implements Decider {
    public boolean decide(FlowContext context) {
        return false;
    }
}
