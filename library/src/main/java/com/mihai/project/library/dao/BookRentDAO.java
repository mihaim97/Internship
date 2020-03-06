package com.mihai.project.library.dao;

import com.mihai.project.library.entity.rent.BookRent;

import java.util.List;

public interface BookRentDAO {

    BookRent registerBookRent(BookRent bookRent);

    List<BookRent> queryAllBookRent();

}
