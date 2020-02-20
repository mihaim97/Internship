package com.mihai.project.library.service;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookDesc;
import com.mihai.project.library.entity.book.BookTag;

import java.util.List;

public interface BookService {
    public Number addBook(Book book);
    public List<Book> queryBooks();
    public List<Author> queryBookAuthor(int bookId);
    public List<BookDesc> queryBookDescriptions(int bookId);
    public List<BookTag> queryBookTags(int bookId);
    public Book queryBook(int id);
    public boolean deleteBook(int id);
    public List<BookDTO> fromBooksToDTO();
    public BookDTO fromBookToDTO(Book book);
    public Book fromDTOToBook (BookDTO bookDTO);
}
