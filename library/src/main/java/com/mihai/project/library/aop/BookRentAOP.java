package com.mihai.project.library.aop;

import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.service.peding.PendingService;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.factory.LibraryFactoryManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BookRentAOP {

    private static final Logger logger = LoggerFactory.getLogger("RENT");

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private PendingService pendingService;

    @AfterReturning(value = "@annotation(com.mihai.project.library.annotation.BookRentAOP)", returning = "bookRent")
    public void afterEmployeeRentABook(JoinPoint joinPoint, Object bookRent) {
        if (bookRent != null) {
            if (bookRent instanceof BookRent) {
                BookRent rent = (BookRent) bookRent;
                logger.info("Book: [" + rent.getBook().getTitle() + ", " + rent.getBook().getId() + "]" + ", Copy: [" + rent.getCopy().getCode() + "]"
                        + " was rented. Employee: " + rent.getUser().getUsername());
            }
        }
    }

    @AfterReturning(value = "@annotation(com.mihai.project.library.annotation.AfterReturningBookAOP)", returning = "bookRentObject")
    public void afterEmployeeReturnARentedBook(JoinPoint joinPoint, Object bookRentObject) {
        if (bookRentObject != null) {
            if (bookRentObject instanceof BookRent) {
                BookRent bookRent = (BookRent) bookRentObject;
                RentRequest rentRequest = rentRequestService.checkForExistingRequest(bookRent.getBook());
                /** If there is a request for this book **/
                if (rentRequest != null) {
                    System.out.println(rentRequest.getId() + " " + rentRequest.getStatus() + " " + rentRequest.getDateRequest());
                    rentRequest.setStatus(RentRequestStatus.WFC.toString());
                    bookRent.getCopy().setStatus(Status.PE.toString());
                    Pending pending = LibraryFactoryManager.getInstance().getPendingInstance();
                    pending.setRentRequestId(rentRequest);
                    pending.setCopyId(bookRent.getCopy().getCode());
                    pendingService.registerPending(pending);
                }
            }
        }
    }

}
