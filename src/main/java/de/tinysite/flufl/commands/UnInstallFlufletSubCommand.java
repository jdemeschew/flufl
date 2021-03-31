package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.services.FlufletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/*
Lists  fluflets according to specified filter.
 */

@CommandLine.Command(name ="uninstall-fluflet", description = "install fluflet")
@Component
@FluflCommand
public class UnInstallFlufletSubCommand implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(UnInstallFlufletSubCommand.class);
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
        flufletService.uninstallFluflet(flufletName);



    }
@Autowired
   public void setFlowableService(FlowableService flowableService) {
       this.flowableService = flowableService;
   }
public void setFlufletService(FlufletService flufletService){
   this.flufletService =flufletService;
}}
