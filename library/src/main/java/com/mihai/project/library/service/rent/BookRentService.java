package com.mihai.project.library.service.rent;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BookRentService {

    BookRent registerBookRent(int bookIdToRent, int userId, int period);

    BookRent checkIfUserAlreadyHasARentForCurrentBook(Book book, User user);

    BookRent returnARentedBook(int bookRentId, float note);

    BookRent queryBookRent(int id);

    List<BookRent> queryAllBookRent();
}
