package com.mihai.project.library.service;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Book;

import java.util.List;

public interface BookService {
    public Number addBook(Book book);
    public List<Book> queryBooks();
    public Book queryBook(int id);
    public List<BookDTO> fromBooksToDTO();
    public BookDTO fromBookToDTO(Book book);
    public Book fromDTOToBook (BookDTO bookDTO);
}
