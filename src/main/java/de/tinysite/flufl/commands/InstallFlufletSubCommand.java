package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.services.FlufletService;
import de.tinysite.flufl.services.impl.FlufletServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

/*
Lists  fluflets according to specified filter.
 */

@CommandLine.Command(name ="install-fluflet", description = "install fluflet")
@Component
@FluflCommand
public class InstallFlufletSubCommand implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(InstallFlufletSubCommand.class);
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
logger.info("installing {} fluflet");


    }
@Autowired
   public void setFlowableService(FlowableService flowableService) {
       this.flowableService = flowableService;
   }
public void setFlufletService(FlufletService flufletService){
   this.flufletService =flufletService;
}}
