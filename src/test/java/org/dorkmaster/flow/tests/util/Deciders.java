package org.dorkmaster.flow.tests.util;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;

public class Deciders {
    public static Decider t = new Decider() {
        @Override
        public boolean decide(FlowContext context) {
            return true;
        }
    };

    public static Decider f = new Decider() {
        @Override
        public boolean decide(FlowContext context) {
            return false;
        }
    };
}
