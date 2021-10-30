package org.dorkmaster.flow.test.decider;

import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.impl.MapFlowContext;
import org.dorkmaster.flow.impl.decider.AndDecider;
import org.dorkmaster.flow.test.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class AndTests  {

    @Test(expected = NoDecidersException.class)
    public void testEmpty() {
        new AndDecider().decide(new MapFlowContext());
    }

    @Test
    public void testTwoTrue() {
        Assert.assertTrue(
                new AndDecider()
                        .addChild(Deciders.t)
                        .addChild(Deciders.t)
                        .decide(new MapFlowContext())
        );
    }

    @Test
    public void testTwoFalse() {
        Assert.assertFalse(
                new AndDecider()
                        .addChild(Deciders.f)
                        .addChild(Deciders.f)
                        .decide(new MapFlowContext())
        );
    }

    @Test
    public void testMixed() {
        Assert.assertFalse(
                new AndDecider()
                        .addChild(Deciders.t)
                        .addChild(Deciders.f)
                        .decide(new MapFlowContext())
        );
    }
}
