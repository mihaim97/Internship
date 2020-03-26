package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.nativequery.TopBookRented;
import com.mihai.project.library.entity.nativequery.TopEmployees;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BookRentDAO {

    BookRent registerBookRent(BookRent bookRent);

    List<BookRent> checkIfUserAlreadyHasARentForCurrentBook(Book book, User user);

    BookRent returnARentedBook(BookRent bookRent, float note);

    BookRent queryBookRent(int id);

    List<BookRent> queryBookRentWithAVStatus(int id);

    List<BookRent> queryAllBookRent();

    List<BookRent> queryAllBookRentView();

    List<TopBookRented> statisticsTopBookRent(int topNum, String startDate, String endDate);

    List<TopBookRented> statisticsTopBookRent(String startDate, String endDate);

    List<TopEmployees> statisticsTopEmployees(String startDate, String endDate);

}
