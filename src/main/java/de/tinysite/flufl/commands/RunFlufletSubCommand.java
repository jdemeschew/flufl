package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.services.FlufletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.IOException;

/*
Lists  fluflets according to specified filter.
 */

@CommandLine.Command(name ="run-fluflet", description = "Run fluflet")
@Component
@FluflCommand
public class RunFlufletSubCommand implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(RunFlufletSubCommand.class);
   @Value("${fluflets.repository.path:}")
   String flufletsRepositoryPath="";
   @Autowired
   FlufletService flufletService;
   String flufletsDir ="";

   @CommandLine.Parameters(index = "0")
   String flufletName;





   private FlowableService flowableService;


    @Override
    public void run()
    {
        String bpmNFilePath =flufletService.getConfigValue("wiremocker","bpmn-file");
        try {
            flowableService.deployProcess(bpmNFilePath);
            flowableService.executeProcess(flufletService.getConfigValue("wiremocker","process-name"));
        } catch (IOException e) {
            logger.info("deployed bpmn process from{}",bpmNFilePath);

        }


    }
@Autowired
   public void setFlowableService(FlowableService flowableService) {
       this.flowableService = flowableService;
   }
public void setFlufletService(FlufletService flufletService){
   this.flufletService =flufletService;
}}
