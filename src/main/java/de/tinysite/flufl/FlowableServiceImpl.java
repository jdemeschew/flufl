package de.tinysite.flufl;

import org.apache.commons.io.FileUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Service class connecting Flufl commands with Flowable engine.
 */
@Service
public class FlowableServiceImpl implements FlowableService {
    private ProcessInstance processInstance;

    private List<String> history = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(FlowableServiceImpl.class);
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private  RepositoryService repositoryService=null;
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    private Deployment deployment = null;
    private final Map<String, Object> taskVars = new HashMap();

    public void setHistory(final List<String> history) {
        this.history = Collections.unmodifiableList(history);
    }





    RuntimeService getRuntimeService() {
        if (runtimeService == null) {
            runtimeService = processEngine.getRuntimeService();
        }
        return runtimeService;
    }


    @Autowired
    protected ProcessEngineConfiguration processEngineConfiguration;

    /**
     * Returns the process definition.
     * @return status of the running Flowable engine.
     */
    @Override
    public String status() {
        try {
            repositoryService.createDeploymentQuery().list();


        } catch (Exception e) {
            return "ERROR";
        } finally {
            return "OK";
        }
    }


    /**
     * Deploys the process from the specified file into the embedded Flowable Engine.
     *
     * @param fileName path and name of the file with the extension .bpmn20.xml containing the process definition.
     * @throws IOException
     */
    @Override
    public void deployProcess( final String fileName) throws IOException {
        final File initialFile = new File(fileName);
        final InputStream targetStream = FileUtils.openInputStream(initialFile);
        deployment = repositoryService.createDeployment()
                .addInputStream(fileName, targetStream)
                .deploy();
        logger.info(String.format("File %s has been deployed", fileName));
    }

    /**
     * Lists all loaded processes.
     */
    @Override
    public void listProcesses() {
        final List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        for (final ProcessDefinition processDefinition : processDefinitions) {
            System.out.println(  "Process name:" + processDefinition.getName());
        }
    }

    /**
     * Executes the process with the specified name.
     * @param processName name of an already deployed process.
     */
    @Override
    public void executeProcess(final String processName) {
        final ProcessDefinition targetProcessDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionName(processName).latestVersion().singleResult();
        logger.info("Executing process {}", targetProcessDefinition.getName());
        processInstance = this.getRuntimeService().startProcessInstanceById(targetProcessDefinition.getId(), taskVars);


    }

    /**
     * Sets a named variable that will be available in executions used in JavaDelegates and ExecutionListeners.
     * @param name Variable name
     * @param value Variable value
     */
    @Override
    public void setVar(final String name, final Object value) {
        this.taskVars.put(name, value);

    }

    /**
     * Lists all running tasks.
     */
    @Override
    public void listTasks() {
        final List<Task> tasks = taskService.createTaskQuery().list();//taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i) + ") " + tasks.get(i).getName());
        }


    }

    /**
     * Completes a running task.
     * @param taskName the id of the task
     */
    @Override
    public void completeTask(String taskName) {

        if (taskService.createTaskQuery().list().size()>0) {
            final Task targetTask = taskService.createTaskQuery().taskName(taskName).list().get(0);
            taskService.complete(targetTask.getId(), taskVars);
            taskService.deleteTask(targetTask.getId());
        } else {
        }
    }

    @Override
    /**
     * Adds a command to the commands history.
     */
    public void addEntryToHistory(final String entry) {
        history.add(entry);

    }

    /**
     * Saves history in a file.
     * @param fileName absolute path to the file the history will be saved in.
     */
    @Override
    public void saveHistory(final String fileName) {
        try {
            FileUtils.writeLines(new File(fileName), Charset.defaultCharset().toString(), history);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Loads history from the specified file.
     * @param fileName absolute path to the file with history entries.
     */
    @Override
    public void loadHistory(final String fileName) {

        try {
            this.history =
                    FileUtils.readLines(new File(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Returns the list with all history entries.
     * @return
     */
    @Override
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * Prints the list of all deployed processes with their ids.
     * @return
     */
    @Override
    public String listHistory() {
        final StringBuffer output = new StringBuffer("entry:").append(System.lineSeparator());
        history.stream().forEach(entry -> {

            output.append(entry).append(System.lineSeparator());
        });
        return output.toString();
    }

    /**
     * Clears the command history.
     */
    @Override
    public void clearHistory() {
        history.clear();
    }

    /**
     * Prints the list of all defined variables with their values.
     * @return
     */
    @Override
    public String listVars() {
        final StringBuffer output = new StringBuffer("Variables:").append(System.lineSeparator());
        taskVars.keySet().stream().forEach(entry -> {

            output.append(entry).append("=")
                    .append(taskVars.get(entry)).append(System.lineSeparator());
        });
        return output.toString();
    }

    /**
     * Generates an image of the specified process.
     * @param processName process name
     */
    @Override
    public void generateImage(String processName) {
        final ProcessDefinition pde = getProcessDefinition(processName);

        final BpmnModel bpmnModel = repositoryService.getBpmnModel(pde.getId());

        final ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        InputStream inputStream;
        final List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .list();

        List<String> activityIds = new ArrayList<>();
        final List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            final List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
            inputStream = diagramGenerator.generateJpgDiagram(bpmnModel);
            File file = new File(processName+".jpg");
            try {
                FileUtils.copyInputStreamToFile(inputStream, file);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }


        }
    }

    /**
     * Returns the process definition for a deployed process with the specified name.
     * @param processName
     * @return process definition of the specified process.
     */
    private ProcessDefinition getProcessDefinition(String processName){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionName(processName).singleResult();
        return processDefinition;
    }

}
