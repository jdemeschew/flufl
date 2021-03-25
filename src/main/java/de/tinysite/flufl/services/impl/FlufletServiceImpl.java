package de.tinysite.flufl.services.impl;

import de.tinysite.flufl.FlowableServiceImpl;
import de.tinysite.flufl.services.FlufletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class FlufletServiceImpl implements FlufletService {
    @Value("${flufl.environment.path:}")
    String fluflEnvironmentPath="";
    private final Logger logger = LoggerFactory.getLogger(FlufletServiceImpl.class);

    @Override
    public List<String> getFlufletsInDir(String pathToDir) {
        File dir = new File(pathToDir);
        List<String> result = Arrays.asList(dir.listFiles()).stream()
                .filter(f ->f.isDirectory()&& Paths.get(f.getAbsolutePath(),f.getName()+".yml").toFile().exists())
                .map(file -> file.getName())
                .collect(Collectors.toList());
        return result;
}}
