package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/* Saves history in the specified file */
@Component
@FluflCommand
@CommandLine.Command(name ="save-history")
 public class SaveHistorySubCommand implements Runnable {
    private Logger logger = LoggerFactory.getLogger(SaveHistorySubCommand.class);
    @CommandLine.Option(names = { "--file"}, description = "File name",required = true)
    String fileName ="";
    private FlowableService flowableService;



     @Override
     public void run() {
flowableService.saveHistory(fileName);

logger.info( "Saved history to {}",fileName);
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
