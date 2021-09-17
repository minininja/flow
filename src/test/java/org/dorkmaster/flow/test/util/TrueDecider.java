package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class TrueDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return true;
    }
}
