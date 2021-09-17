# flow

This is a simple flow engine that uses a yaml based declaration syntax 
to create application flows.  All flows consist of two pieces, a decider 
and a task.  A decider returns a simple boolean (true or false) which 
controls if a task is going to be executed or not.

Flows can be either constructed programmatically or via a factory utilizing 
a simple yaml based syntax.

Sample programmatic construction

```
FlowContext ctx = new SimpleFlow()
    .setDecider(Deciders.t)
    .setTask(Tasks.t)
    .execute(new FlowContext());
```

Alternatively yaml can be provided and used along with a factory.  In the sample yaml 
we declare the classes that can be used to create flows and then a sample flow using
a composite task.

```
class:
    flow:
        simple: org.dorkmaster.flow.impl.flow.SimpleFlow
    decider:
        true: org.dorkmaster.flow.test.util.TrueDecider
    task:
        compositeTask: org.dorkmaster.flow.impl.task.CompositeTask
        marker: org.dorkmaster.flow.test.util.MarkerTask

flow:
    compositeTask:
        flow: simple
            decider: true
        task:
            compositeTask:
                composite: compositeTask
                children:
                    - task: marker
```

Constructing the flow defined in the sample is simple:

```
Flow flow = factory.constructFlow("trivial");
```

