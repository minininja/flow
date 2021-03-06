package org.dorkmaster.flow.test.decider;

import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.impl.MapFlowContext;
import org.dorkmaster.flow.impl.decider.OrDecider;
import org.dorkmaster.flow.test.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class OrTests {

    @Test(expected = NoDecidersException.class)
    public void testEmpty() {
        new OrDecider().decide(new MapFlowContext());
    }

    @Test
    public void testTwoTrue() {
        Assert.assertTrue(
                new OrDecider()
                        .addChild(Deciders.t)
                        .addChild(Deciders.t)
                        .decide(new MapFlowContext())
        );
    }

    @Test
    public void testTwoFalse() {
        Assert.assertFalse(
                new OrDecider()
                        .addChild(Deciders.f)
                        .addChild(Deciders.f)
                        .decide(new MapFlowContext())
        );
    }

    @Test
    public void testMixed() {
        Assert.assertTrue(
                new OrDecider()
                        .addChild(Deciders.t)
                        .addChild(Deciders.f)
                        .decide(new MapFlowContext())
        );
    }
}
