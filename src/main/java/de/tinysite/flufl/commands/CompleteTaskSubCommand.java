package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


/**
 * Completes the task with the specified id
 */
@Component
@FluflCommand
 @CommandLine.Command(name ="complete-task")
 public class CompleteTaskSubCommand implements Runnable {

    @CommandLine.Option(names = {"--id"}, description = "Completes task with the given id",required = true)
    private Integer taskId;
    private FlowableService flowableService;



     @Override
     public void run() {
flowableService.completeTask(taskId);
     }
@Autowired
    public void setFlowableService(final FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
