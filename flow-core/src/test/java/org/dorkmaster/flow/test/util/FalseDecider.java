package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class FalseDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return false;
    }
}
