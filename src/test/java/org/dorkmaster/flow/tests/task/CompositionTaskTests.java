package org.dorkmaster.flow.tests.task;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.NoTasksException;
import org.dorkmaster.flow.tests.CompositionTask;
import org.dorkmaster.flow.tests.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class CompositionTaskTests {

    @Test(expected = NoTasksException.class)
    public void testNone() {
        new CompositionTask().execute(new FlowContext());
    }

    @Test
    public void testOne() {
        FlowContext ctx = new CompositionTask()
                .addTask(Tasks.t)
                .execute(new FlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testTwo() {
        FlowContext ctx = new CompositionTask()
                .addTask(Tasks.t)
                .addTask(Tasks.f)
                .execute(new FlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
        Assert.assertFalse((Boolean) ctx.get(Tasks.F));
    }

}
