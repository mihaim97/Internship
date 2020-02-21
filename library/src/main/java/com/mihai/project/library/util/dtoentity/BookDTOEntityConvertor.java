package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;

import java.util.List;

public interface BookDTOEntityConvertor {

    public List<BookDTOQuery> fromBooksToDTO(List<Book> books);
    public BookDTOQuery fromBookToDTO(Book book);
    public Book fromDTOToBook (BookDTO bookDTO);
}
