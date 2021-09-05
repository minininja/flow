package org.dorkmaster.flow.tests.decider;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.decider.AndDecider;
import org.dorkmaster.flow.decider.NotDecider;
import org.dorkmaster.flow.decider.OrDecider;
import org.dorkmaster.flow.tests.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class CombinedTests {

    @Test
    public void testMixed() {
        Assert.assertTrue(
                new OrDecider()
                    .addDecider(
                        new NotDecider()
                                .setDecider(
                                    new AndDecider()
                                                .addDecider(Deciders.t)
                                                .addDecider(Deciders.f)
                                )
                    )
                    .addDecider(Deciders.f)
                    .decide(new FlowContext())
        );
    }

}

