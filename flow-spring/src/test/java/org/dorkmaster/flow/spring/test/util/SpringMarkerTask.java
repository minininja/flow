package org.dorkmaster.flow.spring.test.util;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;
import org.springframework.stereotype.Component;

@Component(value = "marker")
public class SpringMarkerTask implements Task {
    public static final String MARKER = "marker";

    @Override
    public FlowContext execute(FlowContext context) {
        context.set(MARKER, MARKER);
        return context;
    }
}
