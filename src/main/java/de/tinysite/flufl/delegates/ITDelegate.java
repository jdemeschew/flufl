package de.tinysite.flufl.delegates;

import de.tinysite.flufl.FlowableService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ITDelegate implements JavaDelegate {
    @Autowired
    FlowableService flowableService;
    private static final Logger logger = LoggerFactory.getLogger(ITDelegate.class);
    @Override
    public void execute(DelegateExecution execution) {
logger.info("executing ITDelegate");
    }
}
