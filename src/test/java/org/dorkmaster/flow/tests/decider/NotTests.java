package org.dorkmaster.flow.tests.decider;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.decider.NotDecider;
import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.tests.util.Deciders;
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
                        .setDecider(Deciders.t)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testNotFalse() {
        Assert.assertTrue(
                new NotDecider()
                        .setDecider(Deciders.f
                        )
                        .decide(new FlowContext())
        );
    }
}
