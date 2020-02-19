package com.mihai.project.library.controller;

import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    private void addBook(@RequestBody @Valid BookDTO bookDTO){
        Book book = bookService.fromDTOToBook(bookDTO);
        System.out.println(book.getId() + " " + book.getTitle() + " " + book.getDateAdded());
        bookService.addBook(book);
    }

    @GetMapping(value = "/books", produces = "application/json")
    private List<BookDTO> queryBooks(){
        return bookService.fromBooksToDTO();
    }

}





