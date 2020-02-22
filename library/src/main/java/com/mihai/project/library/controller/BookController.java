package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.IncorrectBookIdException;
import com.mihai.project.library.contralleradvice.exception.NullFieldInBookDTOFoundException;
import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.dto.BookDTO;
import com.mihai.project.library.dto.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.BookService;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.dtoentity.BookDTOEntityConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MyErrorBuilder errorBuilder;

    @PostMapping("/add")
    public ResponseEntity<BookDTOQuery> addBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        Book book = null;
        if(bindingResult.hasErrors()) {
            throw new NullFieldInBookDTOFoundException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }else{
            book = convert.fromDTOToBook(bookDTO);
            return new ResponseEntity<>(convert.fromBookToDTO(bookService.addBook(book)), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTOQuery> queryBooks(){
        return convert.fromBooksToDTO(bookService.queryBooks());
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> querySingleBook(@RequestParam @NotNull @Valid @Min(1) Integer id){
        ResponseEntity<BookDTO> responseEntity = null;
        Book book = bookService.queryBook(id);
        if(book == null)
            throw new IncorrectBookIdException(errorBuilder.getErrorMessageOnIncorrectBookIdException(id));
        responseEntity = new ResponseEntity(convert.fromBookToDTO(book), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping(value = "/delete-book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBook(@RequestParam @NotNull @Valid @Min(1) Integer id){
        if(!bookService.deleteBook(id))
            throw new IncorrectBookIdException(errorBuilder.getErrorMessageOnIncorrectBookIdException(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "/update-book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTOQuery> updateBook(@RequestBody @Valid  BookDTO book, BindingResult bindingResult,  @RequestParam Integer id){
        if(bindingResult.hasErrors())
            throw new NullFieldInBookDTOFoundException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        Book updateBook = bookService.updateBook(convert.fromDTOToBook(book), id);
        if(updateBook == null)
            throw new IncorrectBookIdException(errorBuilder.getErrorMessageOnIncorrectBookIdException(id));
        return new ResponseEntity<>(convert.fromBookToDTO(updateBook), HttpStatus.OK);
    }

}





