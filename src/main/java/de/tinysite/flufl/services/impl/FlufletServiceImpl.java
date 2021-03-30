package de.tinysite.flufl.services.impl;

import de.tinysite.flufl.FlowableServiceImpl;
import de.tinysite.flufl.services.FlufletService;
import liquibase.pro.packaged.M;
import org.apache.commons.io.FileUtils;
import org.h2.store.fs.FileChannelInputStream;
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
import java.util.HashMap;
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
    @Override
    public void uninstallFluflet(String flufletName) {
        File configFile=Paths.get(fluflEnvironmentPath,"fluflets-definitions",flufletName+".yaml").toFile();
        Yaml yaml =new Yaml();
        try {
            Map<String,Object> flufletConfig=yaml.load(new FileInputStream(configFile));
            Map<String,String> unInstallConfigValues = (Map<String, String>) flufletConfig.get("uninstall");
            //installJar((flufletConfig.get("name").toString()),unInstallConfigValues.get("jar"));
            logger.info("uninstalling {} fluflet",flufletName);
            Path jarToBeDeleted =Paths.get(fluflEnvironmentPath,"lib",unInstallConfigValues.get("jar"));
            Path yamlTBeDeleted =Paths.get(fluflEnvironmentPath,"fluflets-definitions",flufletName+".yaml");
            try {
                FileUtils.forceDelete(jarToBeDeleted.toFile());
                FileUtils.forceDelete(yamlTBeDeleted.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void runFluflet(String flufletName) {

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
        Path sourceYaml =Paths.get(flufletsRepositoryPath, flufletName,flufletName+".yaml");
        Path targetYaml =Paths.get(fluflEnvironmentPath,"fluflets-definitions",flufletName+".yaml");
        try {
            FileUtils.copyFile(sourceYaml.toFile(),targetYaml.toFile());
        } catch (IOException e) {
            e.printStackTrace();
    }
}
private Map<String,String> loadInstallConfigValues(String flufletConfigFilePath){
    Yaml yaml =new Yaml();
    Map<String,Object> flufletConfig= null;
    try {
        flufletConfig = yaml.load(new FileInputStream(flufletConfigFilePath));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    Map<String,String> installConfigValues = (Map<String, String>) flufletConfig.get("install");
    return installConfigValues;
}

public String getConfigValue(String fluflet,String value){
    Map<String,String>  configValues =new HashMap<>();
    Path flufletConfigFile  =Paths.get(fluflEnvironmentPath,"fluflets-definitions",fluflet+".yaml");
    Yaml configYaml =new Yaml();
    try {

         configValues =configYaml.load(new FileInputStream(flufletConfigFile.toFile()));
         Path jarToBeDeleted =Paths.get(fluflEnvironmentPath,"li",fluflet,"lib", fluflet+configValues+configValues.get("jar"));
        try {
            FileUtils.forceDelete(jarToBeDeleted.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return configValues.get(value);
}
}
