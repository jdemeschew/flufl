package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


/**
 * Completes the task with the specified id
 */
@Component
@FluflCommand
@CommandLine.Command(name = "complete-task")
public class CompleteTaskSubCommand implements Runnable {

    @CommandLine.Option(names = {"--name"}, description = "Completes task with the given name", required = true)
    private String taskName;
    private FlowableService flowableService;


    @Override
    public void run() {
        flowableService.completeTask(taskName);
    }

    @Autowired
    public void setFlowableService(final FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
