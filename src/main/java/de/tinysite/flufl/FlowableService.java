package de.tinysite.flufl;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
/*
An interface for communication between Flowable engine and Picocli commands
 */
public interface FlowableService {
    default String status() {
        return null;
    }



    void deployProcess(String fileName) throws IOException;
    void  listProcesses();

    void executeProcess(String processName);


    /**
     * Injects variable
     * @param name
     * @param value
     */
    void setVar(String name,  Object value);
    void  listTasks();
    void listFluflets();

    void completeTask(String taskName);
    void addEntryToHistory(String entry);
    void saveHistory(String fileName);
    void loadHistory(String fileName);
    List<String> getHistory();

    String listHistory();
    void clearHistory();

    String listVars();

    void generateImage(String processName);
}

