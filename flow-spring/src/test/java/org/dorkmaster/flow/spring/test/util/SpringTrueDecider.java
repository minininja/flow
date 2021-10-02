package org.dorkmaster.flow.spring.test.util;

import org.dorkmaster.flow.impl.Decider;
import org.dorkmaster.flow.impl.FlowContext;
import org.springframework.stereotype.Component;

@Component(value = "true")
public class SpringTrueDecider implements Decider {
    @Override
    public boolean decide(FlowContext context) {
        return true;
    }
}
