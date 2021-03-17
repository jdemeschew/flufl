package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/* Loads history from the provided file */
@Component
@CommandLine.Command(name ="load-history")
@Qualifier("fluflCommand")
 public class LoadHistorySubCommand implements Runnable {
    private Logger logger = LoggerFactory.getLogger(LoadHistorySubCommand.class);
    @CommandLine.Option(names = { "--file"}, description = "File name")
    private String fileName ="";
    private FlowableService flowableService;



     @Override
     public void run() {
flowableService.loadHistory(fileName);

     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
