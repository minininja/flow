package org.dorkmaster.flow.tests.decider;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.decider.AndDecider;
import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.tests.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class AndTests  {

    @Test(expected = NoDecidersException.class)
    public void testEmpty() {
        new AndDecider().decide(new FlowContext());
    }

    @Test
    public void testTwoTrue() {
        Assert.assertTrue(
                new AndDecider()
                        .addDecider(Deciders.t)
                        .addDecider(Deciders.t)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testTwoFalse() {
        Assert.assertFalse(
                new AndDecider()
                        .addDecider(Deciders.f)
                        .addDecider(Deciders.f)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testMixed() {
        Assert.assertFalse(
                new AndDecider()
                        .addDecider(Deciders.t)
                        .addDecider(Deciders.f)
                        .decide(new FlowContext())
        );
    }
}
