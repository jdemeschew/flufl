package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


@Component
/*
Sets a named parameter that will be available in executions used in JavaDelegates and ExecutionListeners.
If applied after complete-task, the variable will be available in the next running task.
 */
 @CommandLine.Command(name ="set-var")
 public class SetVarSubCommand implements Runnable {
    @CommandLine.Option(names = { "--name"}, description = "Variable name",required = true)
    private String name;

    @CommandLine.Option(names = {"--value"}, description = "Variable value",required = true)
    private Object value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
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
flowableService.setVar(name,value);
     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
}
