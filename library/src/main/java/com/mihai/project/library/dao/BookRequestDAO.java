package com.mihai.project.library.dao;

import com.mihai.project.library.entity.request.BookRequest;

import java.util.List;

public interface BookRequestDAO {

    BookRequest registerBookRequest(BookRequest bookRequest);

    List<BookRequest> queryAllBookRequest();

    List<BookRequest> queryAllBookRequestForConfirmation();

    List<BookRequest> queryAllBookRequestToBuy();

    BookRequest queryBookRequestById(int bookRequestId);

}
