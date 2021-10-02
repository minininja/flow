package org.dorkmaster.flow.impl;

public interface Flow extends Task{
    Flow setDecider(Decider d);
    Flow setTask(Task t);
}
