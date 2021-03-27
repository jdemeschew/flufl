package de.tinysite.flufl.services.impl;

import de.tinysite.flufl.FlowableServiceImpl;
import de.tinysite.flufl.services.FlufletService;
import liquibase.pro.packaged.M;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class FlufletServiceImpl implements FlufletService {
    @Value("${flufl.environment.path:}")
    String fluflEnvironmentPath="";
    @Value("${fluflets.repository.path:}")
    String flufletsRepositoryPath="";
    private final Logger logger = LoggerFactory.getLogger(FlufletServiceImpl.class);

    @Override

    public List<String> getFlufletsInDir(String pathToDir) {
        File dir = new File(pathToDir);
        List<String> result = Arrays.asList(dir.listFiles()).stream()
                .filter(f ->f.isDirectory()&& Paths.get(f.getAbsolutePath(),f.getName()+".yaml").toFile().exists())
                .map(file -> file.getName())
                .collect(Collectors.toList());
        return result;
}

    @Override
    public void installFluflet(String flufletName) {
        File configFile=Paths.get(flufletsRepositoryPath,flufletName,flufletName+".yaml").toFile();
Yaml yaml =new Yaml();
        try {
            Map<String,Object> flufletConfig=yaml.load(new FileInputStream(configFile));
            Map<String,String> installConfigValues = (Map<String, String>) flufletConfig.get("install");
            installJar((flufletConfig.get("name").toString()),installConfigValues.get("source-jar"));
            logger.info("installing {} fluflet",flufletName);
            installYaml("wiremocker");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void installJar(String flufletName ,String jarName) {
        Path sourceJar =Paths.get(flufletsRepositoryPath,flufletName,"target",jarName);
        Path targetJar =Paths.get(fluflEnvironmentPath,"lib",flufletName+".jar");
        try {
            FileUtils.copyFile(sourceJar.toFile(),targetJar.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void installYaml(String flufletName) {
        Path sourceYaml =Paths.get(flufletsRepositoryPath,flufletName,flufletName+".yaml");
        Path targetYaml =Paths.get(fluflEnvironmentPath,"fluflets-definitions",flufletName+".yaml");
        try {
            FileUtils.copyFile(sourceYaml.toFile(),targetYaml.toFile());
        } catch (IOException e) {
            e.printStackTrace();
    }
}}
