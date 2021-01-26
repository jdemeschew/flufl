package de.tinysite.flufl.configuration;

import de.tinysite.flufl.FlowableService;
import de.tinysite.flufl.FlowableServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration()
public class FluflConfiguration {
    private Logger logger = LoggerFactory.getLogger(FluflConfiguration.class);
    @Value("${plesk-api.base.path}")
private String apiBasePath="";
    @Value("${plesk-api.user}")
    private String pleskApiUser="";
    @Value("${plesk-api.password}")
    private String pleskApiPassword="";
    public FlowableService FlowableService() {
        return new FlowableServiceImpl();


    }}