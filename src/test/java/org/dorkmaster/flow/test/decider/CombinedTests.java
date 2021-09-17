package org.dorkmaster.flow.test.decider;

import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.impl.decider.AndDecider;
import org.dorkmaster.flow.impl.decider.NotDecider;
import org.dorkmaster.flow.impl.decider.OrDecider;
import org.dorkmaster.flow.test.util.Deciders;
import org.junit.Assert;
import org.junit.Test;

public class CombinedTests {

    @Test
    public void testMixed() {
        Assert.assertTrue(
                new OrDecider()
                    .addChild(
                        new NotDecider()
                                .addChild(
                                    new AndDecider()
                                                .addChild(Deciders.t)
                                                .addChild(Deciders.f)
                                )
                    )
                    .addChild(Deciders.f)
                    .decide(new FlowContext())
        );
    }

}

