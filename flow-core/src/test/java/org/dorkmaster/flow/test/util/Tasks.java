package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;

public class Tasks {
    public static final String T = "true";
    public static final String F = "false";

    public static Task t = new Task() {
        @Override
        public FlowContext execute(FlowContext context) {
            return context.set(T, true);
        }
    };

    public static Task f = new Task() {
        @Override
        public FlowContext execute(FlowContext context) {
            return context.set(F, false);
        }
    };
}
