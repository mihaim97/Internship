package com.mihai.project.library.util.dtoentity.book;

import com.mihai.project.library.dto.book.BookDTO;
import com.mihai.project.library.dto.book.update.BookDTOID;
import com.mihai.project.library.dto.book.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;

import java.util.Set;

public interface BookDTOEntityConverter {
    /**
     * Book
     **/
    Set<BookDTOQuery> fromBooksToDTO(Set<Book> books);

    BookDTOQuery fromBookToDTO(Book book);

    Book fromDTOToBook(BookDTO bookDTO);

    Book fromDTOIDToBook(BookDTOID bookDTOID);
}
