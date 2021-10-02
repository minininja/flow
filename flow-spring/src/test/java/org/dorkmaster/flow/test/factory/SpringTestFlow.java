package org.dorkmaster.flow.test.factory;

import org.dorkmaster.flow.impl.flow.SimpleFlow;
import org.springframework.stereotype.Component;

@Component(value = "springFlow")
public class SpringTestFlow extends SimpleFlow {
}
