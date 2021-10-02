package org.dorkmaster.flow.impl.task;

import org.dorkmaster.flow.exception.NoTasksException;
import org.dorkmaster.flow.impl.Composite;
import org.dorkmaster.flow.impl.FlowContext;
import org.dorkmaster.flow.impl.Task;

import java.util.LinkedList;
import java.util.List;

public class CompositeTask implements Composite<Task>, Task {
    protected List<Task> tasks = new LinkedList<>();

    @Override
    public FlowContext execute(FlowContext context) {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }

        FlowContext ctx = context;
        for (Task task : tasks) {
            ctx = task.execute(ctx);
        }
        return ctx;
    }

    @Override
    public CompositeTask addChild(Task task) {
        tasks.add(task);
        return this;
    }
}
