package com.mihai.project.library.service.request;

import com.mihai.project.library.contralleradvice.exception.BookRentOrRequestException;
import com.mihai.project.library.dao.BookRequestDAO;
import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.util.enumeration.BookRequestQuery;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
    private BookRequestDAO bookRequestDAO;

    @Autowired
    private MessageBuilder messageBuilder;

    @Override
    @Transactional
    public BookRequest registerBookRequest(BookRequest bookRequest) {
        return bookRequestDAO.registerBookRequest(bookRequest);
    }

    @Override
    @Transactional
    public List<BookRequest> queryAllBookRequest(BookRequestQuery queryType) {
        if (queryType.equals(BookRequestQuery.FOR_CONFIRMATION)) {
            return bookRequestDAO.queryAllBookRequestForConfirmation();
        } else if (queryType.equals(BookRequestQuery.TO_BUY)) {
            return bookRequestDAO.queryAllBookRequestToBuy();
        } else {
            return bookRequestDAO.queryAllBookRequest();
        }
    }

    @Override
    @Transactional
    public BookRequest acceptOrDeclineBookRequest(int bookRequestId, String statusAnswer) {
        BookRequest bookRequest = queryBookRequestById(bookRequestId);
        if (bookRequest == null) {
            String message = String.format(ExceptionMessage.BOOK_REQUEST_NOT_FIND, bookRequestId);
            throw new BookRentOrRequestException(messageBuilder.asJSON(message));
        }
        bookRequest.setStatus(statusAnswer);
        return bookRequest;
    }

    @Override
    @Transactional
    public BookRequest queryBookRequestById(int bookRequestId) {
        return bookRequestDAO.queryBookRequestById(bookRequestId);
    }

}
