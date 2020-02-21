package com.mihai.project.library.controller;

import com.mihai.project.library.aop.QueryBookAspect;
import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.BookService;
import com.mihai.project.library.util.dtoentity.BookDTOEntityConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorDAO authDAO;

    @Autowired
    private BookDTOEntityConvertor convert;

    @PostMapping("/add") // GENERIC
    public ResponseEntity<BookDTOQuery> addBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        Book book = null;
        if(bindingResult.hasErrors()) {
            logger.error(bindingResult.getAllErrors().toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            book = convert.fromDTOToBook(bookDTO);
            return  new ResponseEntity<>(convert.fromBookToDTO(bookService.addBook(book)), HttpStatus.OK);
        }
    }

    @Transactional
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTOQuery> queryBooks(){
        return convert.fromBooksToDTO(bookService.queryBooks());
    }

    @Transactional
    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> querySingleBook(@RequestParam @NotNull @Valid @Min(1) Integer id){
        ResponseEntity<BookDTO> responseEntity = null;
        Book book = bookService.queryBook(id);
        System.out.println(book.getDateAdded().getTime());
        if(book == null )
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            responseEntity = new ResponseEntity(convert.fromBookToDTO(book), HttpStatus.OK);
        return responseEntity;
    }

    @Transactional
    @DeleteMapping(value = "/delete-book")
    public ResponseEntity deleteBook(@RequestParam @NotNull @Valid @Min(1) Integer id){
        if(bookService.deleteBook(id))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST); // de implementat dto pentru raspuns
    }

    @Transactional
    @PutMapping(value = "/update-book")
    public ResponseEntity<BookDTOQuery> updateBook(@RequestBody BookDTO book, @RequestParam int id){
        bookService.updateBook(convert.fromDTOToBook(book), id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}





