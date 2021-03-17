package de.tinysite.flufl.commands;

import org.jline.console.SystemRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * Executes commands from the provided file
 * Effectively, writes commands from the file into history and then executes all commands in history
 */
@Component
@Qualifier("fluflCommand")
@CommandLine.Command(name ="execute-batch")
 public class ExecuteBatchSubCommand implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ExecuteBatchSubCommand.class);
    @CommandLine.Option(names = { "--file"}, description = "File name",required = true)
    String fileName ="";
    private SystemRegistry systemRegistry;
    public void setSystemRegistry(final SystemRegistry systemRegistry) {
        this.systemRegistry = systemRegistry;
    }





     @Override
     public void run() {
logger.info("Executing commands from {}",fileName);
     }

}
