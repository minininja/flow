# flow

This is a simple flow engine that uses a yaml based declaration syntax 
to create application flows.  All flows consist of two pieces, a decider 
and a task.  A decider returns a simple boolean (true or false) which 
controls if a task is going to be executed or not.

