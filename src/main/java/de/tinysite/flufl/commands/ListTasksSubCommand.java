package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/*
Lists all running tasks
 */
@Component
@FluflCommand
 @CommandLine.Command(name ="list-tasks", description = "lists all running tasks")

 public class ListTasksSubCommand implements Runnable {
    @CommandLine.Option(names = { "--name"}, description = "Variable name")
    private String name;

    @CommandLine.Option(names = {"--value"}, description = "Variable value")
    private Object value;


    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }





    private FlowableService flowableService;


     @Override
     public void run()
     {
flowableService.listTasks();

     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
