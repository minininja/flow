package org.dorkmaster.flow.impl.decider;

import org.dorkmaster.flow.Composite;
import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.MisconfiguredDecidersException;
import org.dorkmaster.flow.exception.NoDecidersException;

import java.util.LinkedList;
import java.util.List;

public abstract class CompositeDecider implements Composite<Decider>, Decider {
    protected List<Decider> deciders = new LinkedList<>();

    public CompositeDecider addChild(Decider decider) {
        deciders.add(decider);
        return this;
    }

    protected void validate() {
        if (deciders.isEmpty()) {
            throw new NoDecidersException();
        }
    }

    protected void validate(int expected) {
        validate();
        if (expected != deciders.size()) {
            throw new MisconfiguredDecidersException("Saw " + deciders.size() + " expected " + expected);
        }
    }

    public abstract boolean decide(FlowContext context);
}
