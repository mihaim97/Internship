package com.mihai.project.library.service.rent;

import com.mihai.project.library.entity.rent.BookRent;

import java.util.List;

public interface BookRentService {

    BookRent registerBookRent(BookRent bookRent);

    List<BookRent> queryAllBookRent();
}
