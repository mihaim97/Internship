package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl(@Qualifier("BookDaoHibernate") BookDAO bookDAO, AuthorService authorService){
        this.bookDAO = bookDAO;
    }

    @Override
   // @Transactional
    public Book addBook(Book book) {
        return bookDAO.addBook(book);
    }

    @Override
    @Transactional
    public Set<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    @Transactional
    public Set<Author> queryBookAuthor(int bookId) {
        return bookDAO.queryBookAuthor(bookId);
    }

    @Override
    @Transactional
    public Set<BookTag> queryBookTags(int bookId) {
        return bookDAO.queryBookTags(bookId);
    }

    @Override
    @Transactional
    public Book queryBook(int id) {
        return bookDAO.queryBook(id);
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    @Override
    @Transactional
    public Book updateBook(Book book, int bookId) {
        return bookDAO.updateBook(book, bookId);
    }


}
