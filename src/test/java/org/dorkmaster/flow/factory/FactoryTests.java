package org.dorkmaster.flow.factory;

import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.test.util.MarkerTask;
import org.junit.Assert;
import org.junit.Test;

public class FactoryTests {

    FlowFactory factory = new Factory().load("testFactory");

    @Test
    public void testSimple() {
        Flow flow = factory.constructFlow("trivial");
        Assert.assertNotNull(flow);

        FlowContext result = flow.execute(new FlowContext());
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get(MarkerTask.MARKER));
    }

    @Test
    public void testCompositeDecider() {
        Flow flow = factory.constructFlow("notTrivial");
        Assert.assertNotNull(flow);

        FlowContext result = flow.execute(new FlowContext());
        Assert.assertNotNull(result);
        Assert.assertNull(result.get(MarkerTask.MARKER));
    }
}
