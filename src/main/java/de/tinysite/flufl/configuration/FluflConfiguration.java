package de.tinysite.flufl.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration()
@ComponentScan
public class FluflConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(FluflConfiguration.class);
    @Value("${plesk-api.base.path:}")
private String apiBasePath="";
    @Value("${plesk-api.user:}")
    private String pleskApiUser="";
    @Value("${plesk-api.password:}")
    private String pleskApiPassword="";
    @Value("${fluflets.repository.path:}")
    String flufletsRepositoryPath="";


}