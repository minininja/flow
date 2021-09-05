package org.dorkmaster.flow;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;

public class SimpleFlow implements Flow {
    protected Decider decider;
    protected Task task;

    @Override
    public Flow setDecider(Decider decider) {
        this.decider=decider;
        return this;
    }

    @Override
    public Flow setTask(Task task) {
        this.task = task;
        return this;
    }

    @Override
    public FlowContext execute(FlowContext ctx) {
        if (decider.decide(ctx)) {
            return task.execute(ctx);
        }
        return ctx;
    }
}
