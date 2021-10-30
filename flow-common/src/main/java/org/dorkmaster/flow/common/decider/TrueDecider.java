package org.dorkmaster.flow.common.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class TrueDecider implements Decider {
    public boolean decide(FlowContext context) {
        return true;
    }
}
