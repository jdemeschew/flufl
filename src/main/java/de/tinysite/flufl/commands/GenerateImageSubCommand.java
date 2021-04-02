package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
Generates an image of the current process
 */
@Component
@FluflCommand
 @CommandLine.Command(name ="generate-image")
 public class GenerateImageSubCommand implements Runnable {
    @CommandLine.Option(names = {"--name"}, description = "Generates an image for the process with the given name",required = true)
    private String processName="";
    private FlowableService flowableService;


     @Override
     public void run() {
flowableService.generateImage(processName);
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
