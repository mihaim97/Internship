package com.mihai.project.library.scheduled;

import com.mihai.project.library.service.peding.PendingService;
import com.mihai.project.library.service.rent.BookRentService;
import com.mihai.project.library.service.user.BannedUserService;
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

    @Autowired
    private BannedUserService bannedUserService;

    @Autowired
    private PendingService pendingService;

    @Scheduled(fixedRate = 60000)
    public void markBookRentAsLateIfExist() {
        bookRentService.markBookRentAsLateIfExist();
    }

    @Scheduled(fixedRate = 600000)
    public void deleteBannedUserIfEndDateExpired() {
        bannedUserService.checkIfBannedExpiredAndDelete();
    }

    @Scheduled(fixedRate = 60000)
    public void deletePendingIf24HPassed() {
        pendingService.deletePendingIn24HPassed();
    }

}
