package org.dorkmaster.flow.test.flow;

import org.dorkmaster.flow.impl.MapFlowContext;
import org.dorkmaster.flow.impl.flow.SimpleFlow;
import org.dorkmaster.flow.test.util.Deciders;
import org.dorkmaster.flow.test.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class SimpleFlowTests {

    private static final String EXECUTED = "executed";

    @Test
    public void testExecuted() {
        MapFlowContext ctx = new MapFlowContext();

        new SimpleFlow()
                .setDecider(Deciders.t)
                .setTask(Tasks.t)
                .execute(ctx);

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testNotExecuted() {
        MapFlowContext ctx = new MapFlowContext();

        new SimpleFlow()
                .setDecider(Deciders.f)
                .setTask(Tasks.t)
                .execute(ctx);

        Assert.assertTrue(null == ctx.get(Tasks.T));
    }
}
