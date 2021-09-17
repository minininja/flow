package org.dorkmaster.flow.impl.task;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.NoTasksException;
import org.dorkmaster.flow.test.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class CompositionTaskTests {

    @Test(expected = NoTasksException.class)
    public void testNone() {
        new CompositeTask().execute(new FlowContext());
    }

    @Test
    public void testOne() {
        FlowContext ctx = new CompositeTask()
                .addChild(Tasks.t)
                .execute(new FlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testTwo() {
        FlowContext ctx = new CompositeTask()
                .addChild(Tasks.t)
                .addChild(Tasks.f)
                .execute(new FlowContext());

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
        Assert.assertFalse((Boolean) ctx.get(Tasks.F));
    }

}
