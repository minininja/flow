package org.dorkmaster.flow.test.factory;

import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.factory.FlowFactory;
import org.dorkmaster.flow.factory.StandAloneFactory;
import org.dorkmaster.flow.impl.MapFlowContext;
import org.dorkmaster.flow.test.util.MarkerTask;
import org.dorkmaster.flow.test.util.MarkerTask2;
import org.junit.Assert;
import org.junit.Test;

public class FactoryTests {

    FlowFactory factory = new StandAloneFactory().load("testFactory");

    @Test
    public void testSimple() {
        Flow flow = factory.constructFlow("trivial");
        Assert.assertNotNull(flow);

        FlowContext result = flow.execute(new MapFlowContext());
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get(MarkerTask.MARKER));
    }

    @Test
    public void testCompositeDecider() {
        Flow flow = factory.constructFlow("notTrivial");
        Assert.assertNotNull(flow);

        FlowContext result = flow.execute(new MapFlowContext());
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(MarkerTask.MARKER));
    }

    @Test
    public void testCompositeFlow() {
        Flow flow = factory.constructFlow("compositeFlowTask");
        Assert.assertNotNull(flow);

        FlowContext result = flow.execute(new MapFlowContext());
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get(MarkerTask.MARKER));
        Assert.assertNull(result.get(MarkerTask2.MARKER));
    }
}
