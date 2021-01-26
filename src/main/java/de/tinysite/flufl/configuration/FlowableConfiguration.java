package de.tinysite.flufl.configuration;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableConfiguration {
    @Bean
    ProcessEngineConfiguration engineConfiguration() {
        final ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setJdbcPassword("")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);

        return cfg;
    }
    @Bean
    ProcessEngine processEngine()
    {
        return engineConfiguration().buildProcessEngine();
    }

    @Bean
    RepositoryService repositoryService(){
        return processEngine().getRepositoryService();
    }
    @Bean
    TaskService taskService(){
        return processEngine().getTaskService();
    }




}
