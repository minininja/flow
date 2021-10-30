package org.dorkmaster.flow.test.task;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.NoTasksException;
import org.dorkmaster.flow.impl.MapFlowContext;
import org.dorkmaster.flow.impl.task.CompositeTask;
import org.dorkmaster.flow.test.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class CompositionTaskTests {

    @Test(expected = NoTasksException.class)
    public void testNone() {
        new CompositeTask().execute(new MapFlowContext());
    }

    @Test
    public void testOne() {
        FlowContext ctx = new CompositeTask()
                .addChild(Tasks.t)
                .execute(new MapFlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testTwo() {
        FlowContext ctx = new CompositeTask()
                .addChild(Tasks.t)
                .addChild(Tasks.f)
                .execute(new MapFlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
        Assert.assertFalse((Boolean) ctx.get(Tasks.F));
    }

}
