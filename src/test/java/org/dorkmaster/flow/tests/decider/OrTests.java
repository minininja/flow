package org.dorkmaster.flow.tests.decider;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.decider.OrDecider;
import org.dorkmaster.flow.exception.NoDecidersException;
import org.dorkmaster.flow.tests.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class OrTests {

    @Test(expected = NoDecidersException.class)
    public void testEmpty() {
        new OrDecider().decide(new FlowContext());
    }

    @Test
    public void testTwoTrue() {
        Assert.assertTrue(
                new OrDecider()
                        .addDecider(Deciders.t)
                        .addDecider(Deciders.t)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testTwoFalse() {
        Assert.assertFalse(
                new OrDecider()
                        .addDecider(Deciders.f)
                        .addDecider(Deciders.f)
                        .decide(new FlowContext())
        );
    }

    @Test
    public void testMixed() {
        Assert.assertTrue(
                new OrDecider()
                        .addDecider(Deciders.t)
                        .addDecider(Deciders.f)
                        .decide(new FlowContext())
        );
    }
}
