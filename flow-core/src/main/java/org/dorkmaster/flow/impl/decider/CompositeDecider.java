package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.Composite;
import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.NoDecidersException;

import java.util.LinkedList;
import java.util.List;

public abstract class CompositeDecider implements Composite<Decider>, Decider {
    protected List<Decider> deciders = new LinkedList<>();

    public CompositeDecider addChild(Decider decider) {
        deciders.add(decider);
        return this;
    }

    public boolean decide(FlowContext context) {
        if (deciders.isEmpty()) {
            throw new NoDecidersException();
        }
        return false; // doesn't matter what we return
    }
}