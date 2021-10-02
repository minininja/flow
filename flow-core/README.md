# flow

This is a simple flow engine that uses a yaml based declaration syntax
to create application flows.

There are 3 basic interfaces:

1. Flow - a flow conditionally execute a task based on the outcome of a Decider
2. Decider - evaluates the FlowContext return a boolean go/no go decision.
3. Task - work to be performed if the Flow is executed.

There are several provided logical constructs to allow for composition of several
Deciders or to allow multiple tasks to be executed as a single flow.  Additionally,
the Flow interface itself extends the Task interface which allows a Flow to be used
as a Task for a parent Flow.

Sample programmatic construction

```
FlowContext ctx = new SimpleFlow()
    .setDecider(Deciders.t)
    .setTask(Tasks.t)
    .execute(new FlowContext());
```

Alternatively a YAML file can be provided and used along with a factory.  The YAML defines
two things, the claases to be use for Flows, Deciders and Tasks, and the flows themselves.  
YAML files may import any number of additiona YAML files.  An example is shown below using
the default base YAML file as an import.

The factory supports declarative definition of flows using the definitions and supports

* Creation of singular and composite deciders and tasks.
* Injection of child Flows as tasks within parent flows, although not by name currently.

```
imports:
  - flowFactoryDefaults 

class:
  decider:
    true: org.dorkmaster.flow.test.factory.SpringTrueDecider
    false: org.dorkmaster.flow.test.factory.FalseDecider
  task:
    marker: org.dorkmaster.flow.test.factory.MarkerTask
    marker2: org.dorkmaster.flow.test.factory.MarkerTask2

flows:
  trivial:
    flow: simple
    decider: true
    task: marker

  compositeDecider:
    flow: simple
    decider:
      composite: not
      children:
        - decider: true
    task: marker

  compositeTaskWithChildFlow:
    flow: simple
    decider: true
    task:
      composite: compositeTask
      children:
        - flow: simple
          decider: false
          task: marker2
        - task: marker

```

Constructing the flow defined in the sample is simple:

```
Flow flow = new Factory()
    .load("testFactory")
    .constructFlow("compositeTaskWithChildFlow");
```

