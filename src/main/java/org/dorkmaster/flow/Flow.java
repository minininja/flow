package org.dorkmaster.flow;

public interface Flow extends Task{
    Flow setDecider(Decider d);
    Flow setTask(Task t);
}
