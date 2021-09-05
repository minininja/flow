package org.dorkmaster.flow.tests;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.Task;
import org.dorkmaster.flow.exception.NoTasksException;

import java.util.LinkedList;
import java.util.List;

public class CompositionTask implements Task {
    protected List<Task> tasks = new LinkedList<>();

    public CompositionTask addTask(Task task) {
        tasks.add(task);
        return this;
    }

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
}
