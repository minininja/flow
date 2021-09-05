package org.dorkmaster.flow;

public interface Flow {
    Flow setDecider(Decider d);
    Flow setTask(Task t);
    FlowContext execute(FlowContext ctx);
}
