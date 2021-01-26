package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.FlowableServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


/* Returns the status of the Flowable Engine */
@Component
 @CommandLine.Command(name ="status",description="outputs Flowable engine status")
 public class StatusSubCommand implements Runnable {
    private Logger logger = LoggerFactory.getLogger(FlowableServiceImpl.class);
    private FlowableService flowableService;


     @Override
     public void run() {
         final String status = flowableService.status();
         if (!status.equals("OK")){
             logger.error("Flowable is not running properly");
         }else{
             logger.info("Flowable is running properly");
         }
flowableService.status();
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
