package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
/* Lists all loaded processes */

@Component
@FluflCommand
@CommandLine.Command(name = "list-processes")
public class ListProcessesSubCommand implements Runnable {
    private FlowableService flowableService;

    @Override
    public void run() {

        flowableService.listProcesses();
    }
    @Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
