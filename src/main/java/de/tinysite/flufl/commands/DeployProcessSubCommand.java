package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.IOException;

/**
 * Deploys the process defined in the specified file
 */
@Component
@Qualifier("fluflCommand")
@CommandLine.Command(name="deploy-process")
public class DeployProcessSubCommand implements Runnable {
    @CommandLine.Option(names = { "--file"}, description = "File name",required=true)
    String fileName ="";
    private FlowableService flowableService;


    @Override
    public void run() {

        try {
            flowableService.deployProcess(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    public void setFlowableService(final FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
