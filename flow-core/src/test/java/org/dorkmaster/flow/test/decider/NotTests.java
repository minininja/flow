package org.dorkmaster.flow.test.decider;

import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.impl.FlowContext;
import org.dorkmaster.flow.impl.decider.NotDecider;
import org.dorkmaster.flow.test.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class NotTests {

    @Test(expected = NoDecidersException.class)
    public void testEmpty() {
        new NotDecider().decide(new FlowContext());
    }

    @Test
    public void testNotTrue() {
        Assert.assertFalse(
                new NotDecider()
                        .addChild(Deciders.t)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testNotFalse() {
        Assert.assertTrue(
                new NotDecider()
                        .addChild(Deciders.f)
                        .decide(new FlowContext())
        );
    }
}
