package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BookRentDAO {

    BookRent registerBookRent(BookRent bookRent, CopyStock copyStock, User user, int period);

    List<BookRent> checkIfUserAlreadyHasARentForCurrentBook(Book book, User user);

    BookRent returnARentedBook(BookRent bookRent, float note);

    BookRent queryBookRent(int id);

    List<BookRent> queryAllBookRent();

}
