package com.mihai.project.library.controller;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorDAO authDAO;

    @PostMapping("/add")
    private void addBook(@RequestBody @Valid BookDTO bookDTO){
        Book book = bookService.fromDTOToBook(bookDTO);
        bookService.addBook(book);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    private List<BookDTO> queryBooks(){
        return bookService.fromBooksToDTO();
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<BookDTO> querySingleBook(@RequestParam @NotNull @Min(1) int id){
        ResponseEntity<BookDTO> responseEntity = null;
        Book book = bookService.queryBook(id);
        if(book == null )
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        else
            responseEntity = new ResponseEntity(bookService.fromBookToDTO(book), HttpStatus.OK);
        return responseEntity;
    }

}





