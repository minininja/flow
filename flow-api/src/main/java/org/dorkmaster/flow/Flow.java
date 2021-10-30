package org.dorkmaster.flow;

/**
 * Defines the structure for a flow.  A flow defines an if/then contract where the conditional
 * is the Decider and the then is the Task.
 */
public interface Flow extends Task {
    Flow setDecider(Decider d);
    Flow setTask(Task t);
}
