

Flufl is an interactive command line tool that allows for walking through BPMN processes defined for the Flowable BPMN Engine.

It can be used during development or QA of BPMN processes.

In batch mode Flufl supports executing the processes on the command line and saving the commands in a batch file afterwards.
Later you can execute the recorded script providing the path to the batch file as the first parameter when starting the Flufl app.
This allows for writing pretty sophisticated Java/Spring-based scripts where individual steps are developed in Java and then glued together using a BPMN file.
In short, Flufl can integrate BPMN processes into command line applications running in Spring context and thus can use the functionality provided by this framework.

Start Flufl by executing `java -jar flufl.jar`

You can use the following commands to navigate and control the BPM process.


---
# Commands

## deploy-process

Deploys the process from the given file into the embedded Flowable Engine.

Usage: `deploy-process --file=full path to file`


## execute-process

Executes the process with the specified name.

Usage: `execute-process --name=process name`

Note: You can use `list-processes` to display the list of all deployed processes.


## complete-task

Completes the task with the specified id.

Usage: `complete-task --id=ID`

Note: You can use the `list-tasks` command to display all running tasks with their IDs.


## generate-image

Generates an image of the current process.

Usage: `generate-image`


## clear-history

Clears the command history.

Usage: `clear-history`

Note: You can use `save-history` to save the commands to a text file that can be later executed directly.


## list-history

Lists all commands executed since Flufl start or since the last call to `clear-history`.

Usage: `list-history`


## list-processes

Lists all loaded processes.

Usage: `list-processes`


## list-tasks

Lists all running tasks.

Usage: `list-tasks --name=Variable name --value=Variable value`


## list-vars

Lists all defined vars.

Usage: `list-vars`


## save-history

Saves history in the specified file.

Usage: `save-history --file=File name`

Later you can run Flufl in batch mode by providing this file as first argument when starting it from the command line: `java -jar path-to-file`


## load-history

Loads history from the specified file.

Usage: `load-history --file=File name`


## set-var

Sets a named parameter that will be available in executions used in `JavaDelegates` and `ExecutionListeners`.

Usage: `set-var --name=Variable name --value=Variable value`

Note: This command is only effective when applied before `execute-process` or after `complete-task`. If applied after `complete-task`, the variable will be available in the next running task.


## status

Returns the status of the Flowable Engine.

Usage: `status`






----
