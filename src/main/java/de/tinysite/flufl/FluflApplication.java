package de.tinysite.flufl;

import de.tinysite.flufl.commands.*;
import org.fusesource.jansi.AnsiConsole;
import org.jline.console.SystemRegistry;
import org.jline.console.impl.SystemRegistryImpl;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.Parser;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.shell.jline3.PicocliCommands;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class FluflApplication implements CommandLineRunner {
	private Logger logger =LoggerFactory.getLogger(FluflApplication.class);
	private  static final  Path CWD = Paths.get(System.getProperty("user.dir"));
@Autowired
	FlowableService flowableService;

		@CommandLine.Command(name = "flufl",
				description = {
						"Example interactive shell with completion and autosuggestions. " +
								"Hit @|magenta <TAB>|@ to see available commands.",
						"Hit @|magenta ALT-S|@ to toggle tailtips.",
						""},
				footer = {"", "Press Ctl-D to exit."},
				subcommands = {

		})
		static class CliCommands implements Runnable {
	private 		Logger logger = LoggerFactory.getLogger(CliCommands.class);
	private 		LineReaderImpl reader;
			private PrintWriter out;

			@Override
			public void run() {
			}
		}






	private AnsiConsole ansiConsole;
	private Terminal terminal;
	private String prompt = "#";
	private String rightPrompt = null;
	private SystemRegistry systemRegistry;
List<Runnable> commands;

	@Autowired
	public  FluflApplication(
			@FluflCommand
			List<Runnable> commands){
		this.commands =commands;


		try {
			terminal = TerminalBuilder.builder().build();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(FluflApplication.class, args);
	}

	@Override
	public void run(String... args) {

	final 	CommandLine commandLine = new CommandLine(new CliCommands());
	for(Runnable command:commands)
		commandLine.addSubcommand(command);

	final 	PicocliCommands picocliCommands = new PicocliCommands(CWD,commandLine);
	final 	LineReader  reader = LineReaderBuilder.builder().terminal(terminal)
				.variable(LineReader.LIST_MAX,50).build();
		final Parser parser = new DefaultParser();
		systemRegistry = new SystemRegistryImpl(parser, terminal, null, null);
		systemRegistry.setCommandRegistries(picocliCommands);
		systemRegistry.cleanUp();
		if (args.length>=1){

			flowableService.loadHistory(args[args.length-1]);
			logger.info("Executing commands from {}",args[args.length-1]);

			executeHistory();
		}else{
			boolean inCommandLineLoop =true;
		while (inCommandLineLoop){
			try{
				systemRegistry.cleanUp();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

			String line =reader.readLine(prompt,rightPrompt,(MaskingCallback)null,null);
			if (("execute-history".equals(line))) {
				executeHistory();



				flowableService.addEntryToHistory(line);
				try {
					systemRegistry.execute(line);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}


			}else
				{
				flowableService.addEntryToHistory(line);

			try {
				systemRegistry.execute(line);

			} catch (Exception e) {
				logger.error(e.getMessage());
			}finally {
				systemRegistry.cleanUp();
			}
		}

		} //end of commandLineLoop;
		}

}

	private void executeHistory() {
		List<String> historyCommands=flowableService.getHistory();
		Iterator<String> commandsIterator =historyCommands.listIterator();
		while (commandsIterator.hasNext())
			try {
				String command =commandsIterator.next();
				systemRegistry.execute(command);
				commandsIterator.remove();
				systemRegistry.terminal().flush();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}finally {
				try {
				} catch (Exception e) {
					logger.error(e.getMessage());
				}

	}
	}}
