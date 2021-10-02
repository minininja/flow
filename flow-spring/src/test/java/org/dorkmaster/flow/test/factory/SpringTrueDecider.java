package org.dorkmaster.flow.test.factory;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.FlowContext;
import org.springframework.stereotype.Component;

@Component(value = "true")
public class SpringTrueDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return true;
    }
}
