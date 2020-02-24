package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.user.User;

import java.util.List;
import java.util.Set;

public interface BookDTOEntityConverter {
    /**Book**/
    public Set<BookDTOQuery> fromBooksToDTO(Set<Book> books);
    public BookDTOQuery fromBookToDTO(Book book);
    public Book fromDTOToBook (BookDTO bookDTO);
}
