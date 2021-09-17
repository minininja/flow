package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;

public class MarkerTask implements Task {
    public static final String MARKER = "marker";

    @Override
    public FlowContext execute(FlowContext context) {
        context.set(MARKER, MARKER);
        return context;
    }
}
