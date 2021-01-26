package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


/*
Lists all defined params
 */
@Component
 @CommandLine.Command(name ="list-params")
 public class ListParamsSubCommand implements Runnable {
    private FlowableService flowableService;


     @Override
     public void run() {
System.out.println(flowableService.listVars());
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
