package com.mihai.project.library.util.dtoentity;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.util.MyObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDTOEntityConvertorImpl implements BookDTOEntityConvertor{

    @Override
    public List<BookDTOQuery> fromBooksToDTO(List<Book> books) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        List<BookDTOQuery> booksDTO = new ArrayList<>();
        books.stream().forEach(b->{
            booksDTO.add(mapper.map(b, (Type) BookDTOQuery.class));
        });
        return booksDTO;
    }

    @Override
    public BookDTOQuery fromBookToDTO(Book book) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        BookDTOQuery bookDTOQuery = mapper.map(book, BookDTOQuery.class);
        return bookDTOQuery;
    }

    @Override
    public Book fromDTOToBook(BookDTO bookDTO) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        Book book = mapper.map(bookDTO, Book.class);
        return book;
    }
}
