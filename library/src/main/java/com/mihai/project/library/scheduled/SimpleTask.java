package com.mihai.project.library.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SimpleTask {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTask.class);

    //@Scheduled(fixedRate = 6000)
    public void simpleTask(){
        logger.info("Scheduled message!");
    }
}
