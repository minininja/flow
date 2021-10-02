package org.dorkmaster.flow.test.factory;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class NonSpringFalseDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return false;
    }
}
