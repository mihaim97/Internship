package com.mihai.project.library.service.request;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.user.User;

public interface RentRequestService {

    RentRequest registerRentRequest(int bookId, int userId);

    RentRequest checkIfUserHasARequestForCurrentBook(Book book, User user);

    BookRent checkIfUserAlreadyHasAvailableBookRent(Book book, User user);

    RentRequest checkForExistingRequest(Book book);

    Pending registerPending(Pending pending);

}
