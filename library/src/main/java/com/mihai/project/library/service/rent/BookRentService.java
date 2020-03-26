package com.mihai.project.library.service.rent;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.enumeration.BookRentQueryType;

import java.util.List;

public interface BookRentService {

    BookRent registerBookRent(int bookIdToRent, User user, int period);

    boolean checkIfUserAlreadyHasARentForCurrentBook(Book book, User user);

    BookRent returnARentedBook(int bookRentId, float note, User user);

    BookRent queryBookRent(int id);

    BookRent queryBookRentWithAVStatus(int id);

    List<BookRent> markBookRentAsLateIfExist();

    List<BookRent> queryAllBookRent(BookRentQueryType type);

    BookRent extendRent(int bookRentId, User user);

}
