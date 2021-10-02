package org.dorkmaster.flow.test.task;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.impl.flow.SimpleFlow;
import org.dorkmaster.flow.impl.task.FlowTask;
import org.dorkmaster.flow.test.util.Deciders;
import org.dorkmaster.flow.test.util.Tasks;
import org.junit.Assert;
import org.junit.Test;

public class FlowTaskTests {

    private static final String EXECUTED = "executed";

    @Test
    public void testHappyTrue() {
        FlowContext ctx = new FlowContext();

        new SimpleFlow()
                .setDecider(Deciders.t)
                .setTask(
                        (FlowTask) new FlowTask()
                            .setDecider(Deciders.t)
                            .setTask(Tasks.t)
                ).execute(ctx);

        Assert.assertTrue((Boolean) ctx.get(Tasks.T));
    }

    @Test
    public void testHappyFalse() {
        FlowContext ctx = new FlowContext();

        new SimpleFlow()
                .setDecider(Deciders.t)
                .setTask(
                        (FlowTask) new FlowTask()
                                .setDecider(Deciders.f)
                                .setTask(Tasks.t)
                ).execute(ctx);

        Assert.assertNull(ctx.get(Tasks.T));
    }
}
