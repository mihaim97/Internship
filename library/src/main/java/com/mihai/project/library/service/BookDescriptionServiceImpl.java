package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDescriptionDAO;
import com.mihai.project.library.dao.jdbctemplate.BookDescriptionDAOImpl;
import com.mihai.project.library.entity.book.BookDesc;
import org.springframework.stereotype.Service;

@Service
public class BookDescriptionServiceImpl implements BookDescriptionService {

    private BookDescriptionDAO bookDescriptionDAO;

    public BookDescriptionServiceImpl(BookDescriptionDAO bookDescriptionDAO){this.bookDescriptionDAO = bookDescriptionDAO;}

    @Override
    public void addBookDescription(int bookId, BookDesc bookDesc) {
        bookDescriptionDAO.addBookDescription(bookId, bookDesc);
    }
}
