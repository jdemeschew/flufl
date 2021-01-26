package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/*
Lists all commands executed since Flufl start or since the last call to the clear-history command.
 */
@Component
 @CommandLine.Command(name ="list-history")
 public class ListHistorySubCommand implements Runnable {
    private FlowableService flowableService;


     @Override
     public void run() {
System.out.println(flowableService.listHistory());
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
