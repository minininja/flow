package org.dorkmaster.flow.common.task;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;

public class MarkerTask implements Task {
    public static final String MARKER = "marker";

    public FlowContext execute(FlowContext context) {
        context.set(MARKER, MARKER);
        return context;
    }
}
