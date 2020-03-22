package com.mihai.project.library.service.request;

import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.util.enumeration.BookRequestQuery;

import java.util.List;

public interface BookRequestService {

    BookRequest registerBookRequest(BookRequest bookRequest);

    List<BookRequest> queryAllBookRequest(BookRequestQuery queryType);

    BookRequest acceptOrDeclineBookRequest(int bookId, String statusAnswer);

    BookRequest queryBookRequestById(int bookRequestId);

}
