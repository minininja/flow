package org.dorkmaster.flow.test.util;

import org.dorkmaster.flow.Decider;
import org.dorkmaster.flow.impl.decider.NotDecider;

public class Deciders {
    public static Decider t = new TrueDecider();
    public static Decider f = new NotDecider().addChild(new TrueDecider());
}
