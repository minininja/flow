package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;

public class TrueDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return true;
    }
}
