package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookDesc;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.util.MyObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    private AuthorService authorService;

    public BookServiceImpl(BookDAO bookDAO, AuthorService authorService){
        this.bookDAO = bookDAO;
        this.authorService = authorService;
    }

    @Override
    @Transactional
    public Number addBook(Book book) {
        Number bookNumber = bookDAO.addBook(book);
        return bookNumber;
    }

    @Override
    @Transactional
    public List<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    @Transactional
    public List<Author> queryBookAuthor(int bookId) {
        return bookDAO.queryBookAuthor(bookId);
    }

    @Override
    @Transactional
    public List<BookDesc> queryBookDescriptions(int bookId) {
        return bookDAO.queryBookDescriptions(bookId);
    }

    @Override
    @Transactional
    public List<BookTag> queryBookTags(int bookId) {
        return bookDAO.queryBookTags(bookId);
    }

    @Override
    @Transactional
    public Book queryBook(int id) {
        return bookDAO.queryBook(id);
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    @Override
    public List<BookDTO> fromBooksToDTO() {
        List<Book> book = queryBooks();
        ModelMapper mapper = MyObjectMapper.getMapper();
        List<BookDTO> booksDTO = new ArrayList<>();
        book.stream().forEach(b->{
            booksDTO.add(mapper.map(b, (Type) BookDTO.class));
        });
        return booksDTO;
    }

    @Override
    public BookDTO fromBookToDTO(Book book) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        return bookDTO;
    }

    @Override
    public Book fromDTOToBook(BookDTO bookDTO) {
        ModelMapper mapper = MyObjectMapper.getMapper();
        Book book = mapper.map(bookDTO, Book.class);
        return book;
    }


}
