package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.book.BookDTO;
import com.mihai.project.library.dto.book.update.BookDTOID;
import com.mihai.project.library.dto.book.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.util.mapper.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

@Component
public class BookDTOEntityConverterImpl implements BookDTOEntityConverter {

    @Override
    public Set<BookDTOQuery> fromBooksToDTO(Set<Book> books) {
        ModelMapper mapper = ModelMapperUtil.getMapper();
        Set<BookDTOQuery> booksDTO = new HashSet<>();
        books.stream().forEach(b->{
            booksDTO.add(mapper.map(b, (Type) BookDTOQuery.class));
        });
        return booksDTO;
    }

    @Override
    public BookDTOQuery fromBookToDTO(Book book) {
        ModelMapper mapper = ModelMapperUtil.getMapper();
        BookDTOQuery bookDTOQuery = mapper.map(book, BookDTOQuery.class);
        return bookDTOQuery;
    }

    @Override
    public Book fromDTOToBook(BookDTO bookDTO) {
        ModelMapper mapper = ModelMapperUtil.getMapper();
        Book book = mapper.map(bookDTO, Book.class);
        return book;
    }

    @Override
    public Book fromDTOIDToBook(BookDTOID bookDTOID) {
        return ModelMapperUtil.getMapper().map(bookDTOID, Book.class);
    }
}
