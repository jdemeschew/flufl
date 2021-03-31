package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
Executes the process with the provided name
 */
@Component
@FluflCommand
@CommandLine.Command(name = "execute-process")
public class ExecuteProcessSubCommand implements Runnable {
    @CommandLine.Option(names = {"--name"}, description = "Completes process with the given name",required = true)
    private String processName="";
    private FlowableService flowableService;


    @Override
    public void run() {

        flowableService.executeProcess(processName);
    }
    @Autowired
    public void setFlowableService(final FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
