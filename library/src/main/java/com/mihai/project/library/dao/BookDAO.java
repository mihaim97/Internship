package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;

import java.util.List;

public interface BookDAO {
    public Book addBook(Book book);
    public List<Book> queryBooks();
    public List<Author> queryBookAuthor(int bookId);
    public List<BookTag> queryBookTags(int bookId);
    public Book queryBook(int id);
    public boolean deleteBook(int id);
    public Book updateBook(Book book, int bookId);
}
