package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Book;

import java.util.List;

public interface BookDAO {
    public Number addBook(Book book);
    public List<Book> queryBooks();
    public Book queryBook(int id);
}
