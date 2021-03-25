package de.tinysite.flufl.commands;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.services.FlufletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

/*
Lists  fluflets according to specified filter.
 */

 @CommandLine.Command(name ="list-fluflets", description = "lists fluflets")
 @Component
 @FluflCommand
 public class ListFlufletsSubCommand implements Runnable {
    @Value("${fluflets.repository.path:}")
    String flufletsRepositoryPath="";
    @Autowired
    FlufletService flufletService;
    String flufletsDir ="";

    @CommandLine.Parameters(index = "0")
    String filter;





    private FlowableService flowableService;


     @Override
     public void run()
     {

         List<String> fluflets =flufletService.getFlufletsInDir(flufletsRepositoryPath);
         fluflets.stream().forEach(
                 fluflet ->System.out.println(fluflet));

     }
@Autowired
    public void setFlowableService(FlowableService flowableService) {
        this.flowableService = flowableService;
    }
public void setFlufletService(FlufletService flufletService){
    this.flufletService =flufletService;
}}
