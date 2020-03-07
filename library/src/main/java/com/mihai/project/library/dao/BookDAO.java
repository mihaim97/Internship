package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;

import java.util.List;
import java.util.Set;

public interface BookDAO {
    Book addBook(Book book);

    Set<Book> queryBooks();

    Set<Author> queryBookAuthor(int bookId);

    Set<Tag> queryBookTags(int bookId);

    Book queryBook(int id);

    Book queryBookUsingFind(int id);

    List<Book> queryBookAndGetList(int id);

    boolean deleteBook(int id);

    Book updateBook(Book book, int bookId);

    Book updateBookUsingTagAndAuthorId(Book book);
}
