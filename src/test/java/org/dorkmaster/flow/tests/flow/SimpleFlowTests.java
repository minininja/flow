package org.dorkmaster.flow.tests.flow;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.SimpleFlow;
import org.dorkmaster.flow.tests.util.Deciders;
import org.dorkmaster.flow.tests.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class SimpleFlowTests {

    private static final String EXECUTED = "executed";

    @Test
    public void testExecuted() {
        FlowContext ctx = new FlowContext();

        new SimpleFlow()
                .setDecider(Deciders.t)
                .setTask(Tasks.t)
                .execute(ctx);

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testNotExecuted() {
        FlowContext ctx = new FlowContext();

        new SimpleFlow()
                .setDecider(Deciders.f)
                .setTask(Tasks.t)
                .execute(ctx);

        Assert.assertTrue(null == ctx.get(Tasks.T));
    }
}
