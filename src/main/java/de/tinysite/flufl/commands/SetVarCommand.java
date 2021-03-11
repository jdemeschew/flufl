package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.FlowableServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


@Component
/*
Sets a named parameter that will be available in executions used in JavaDelegates and ExecutionListeners.
If applied after complete-task, the param  will be available in the next running task.
 */
 @CommandLine.Command(name ="set-var")
 public class SetVarCommand implements Runnable {
    @CommandLine.Option(names = { "--name"}, description = "var name",required = true)
    private String name;

    @CommandLine.Option(names = {"--value"}, description = "var value",required = true)
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

    /* Returns the status of the Flowable Engine */
    @Component
     @CommandLine.Command(name ="status",description="outputs Flowable engine status")
     public static class StatusSubCommand implements Runnable {
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
}
