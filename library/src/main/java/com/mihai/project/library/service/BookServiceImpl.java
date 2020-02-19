package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    public Book queryBook(int id) {
        return null;
    }

    @Override
    public List<BookDTO> fromBooksToDTO() {
        List<Book> book = queryBooks();
        ModelMapper mapper = new ModelMapper();
        List<BookDTO> booksDTO = new ArrayList<>();
        book.stream().forEach(b->{
            booksDTO.add(mapper.map(b, (Type) BookDTO.class));
        });
        return booksDTO;
    }

    @Override
    public Book fromDTOToBook(BookDTO bookDTO) {
        ModelMapper mapper = new ModelMapper();
        Book book = mapper.map(bookDTO, Book.class);
        return book;
    }
}
