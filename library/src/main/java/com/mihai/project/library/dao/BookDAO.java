package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;

import java.util.List;

public interface BookDAO {
    Book addBook(Book book);
    List<Book> queryBooks();
    List<Author> queryBookAuthor(int bookId);
    List<BookTag> queryBookTags(int bookId);
    Book queryBook(int id);
    boolean deleteBook(int id);
    Book updateBook(Book book, int bookId);
}
