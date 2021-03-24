package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/*
Lists  fluflets
 */
@Component
@FluflCommand
 @CommandLine.Command(name ="list-fluflets", description = "lists fluflets")

 public class ListFlufletsSubCommand implements Runnable {






    private FlowableService flowableService;


     @Override
     public void run()
     {
flowableService.listFluflets();

     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
