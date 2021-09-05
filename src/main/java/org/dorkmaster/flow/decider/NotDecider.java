package org.dorkmaster.flow.decider;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.NoDecidersException;

public class NotDecider implements Decider {

    protected Decider decider;

    public NotDecider setDecider(Decider decider) {
        this.decider = decider;
        return this;
    }

    @Override
    public boolean decide(FlowContext context) {
        if (null == decider) {
            throw new NoDecidersException();
        }

        return !decider.decide(context);
    }
}
