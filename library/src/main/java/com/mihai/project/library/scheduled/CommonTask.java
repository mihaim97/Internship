package com.mihai.project.library.scheduled;

import com.mihai.project.library.service.rent.BookRentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CommonTask {

    private static final Logger logger = LoggerFactory.getLogger(CommonTask.class);

    @Autowired
    private BookRentService bookRentService;

    @Scheduled(fixedRate = 60000)
    public void markBookRentAsLateIfExist() {
        bookRentService.markBookRentAsLateIfExist();
    }
}
