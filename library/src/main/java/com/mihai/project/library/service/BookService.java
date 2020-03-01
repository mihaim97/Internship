package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;

import java.util.Set;

public interface BookService {
    public Book addBook(Book book);

    public Set<Book> queryBooks();

    public Set<Author> queryBookAuthor(int bookId);

    public Set<Tag> queryBookTags(int bookId);

    public Book queryBook(int id);

    public boolean deleteBook(int id);

    public Book updateBook(Book book, int bookId);

    Book updateBookUsingTagAndAuthorId(Book book);
}
