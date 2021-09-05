package org.dorkmaster.flow.tests.factory;

import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.factory.Factory;
import org.dorkmaster.flow.factory.FlowFactory;
import org.junit.Assert;
import org.junit.Test;

public class FactoryTests {

    FlowFactory factory = new Factory();

    @Test
    public void testSimple() {
        Flow flow = factory.load("testFactory").constructFlow("simple");
        Assert.assertNotNull(flow);
    }
}
