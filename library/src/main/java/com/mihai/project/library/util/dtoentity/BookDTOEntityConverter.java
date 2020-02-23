package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface BookDTOEntityConverter {
    /**Book**/
    public List<BookDTOQuery> fromBooksToDTO(List<Book> books);
    public BookDTOQuery fromBookToDTO(Book book);
    public Book fromDTOToBook (BookDTO bookDTO);
}
