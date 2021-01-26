package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * Clears the command history
 */
@Component
 @CommandLine.Command(name ="clear-history")
 public class ClearHistorySubCommand implements Runnable {
    private FlowableService flowableService;


     @Override
     public void run() {
flowableService.clearHistory();
     }
@Autowired
    public void setFlowableService(final FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
