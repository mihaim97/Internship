package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.NullFieldInBookDTOFoundException;
import com.mihai.project.library.dto.book.BookDTO;
import com.mihai.project.library.dto.book.update.BookDTOID;
import com.mihai.project.library.dto.book.BookDTOQuery;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.BookService;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.dtoentity.BookDTOEntityConverter;
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
import java.util.Set;

@RestController
@RequestMapping(path = "book")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDTOEntityConverter convert;

    @Autowired
    private MyErrorBuilder errorBuilder;

    @PostMapping("/add")
    public ResponseEntity<BookDTOQuery> addBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        Book book;
        if (bindingResult.hasErrors()) {
            throw new NullFieldInBookDTOFoundException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        } else {
            book = convert.fromDTOToBook(bookDTO);
            return new ResponseEntity<>(convert.fromBookToDTO(bookService.addBook(book)), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<BookDTOQuery> queryBooks() {
        return convert.fromBooksToDTO(bookService.queryBooks());
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity querySingleBook(@RequestParam @NotNull @Valid @Min(1) Integer id) {
        Book book = bookService.queryBook(id);
        if (book == null) {
            return noBookWasFind(id);
        }
        return new ResponseEntity(convert.fromBookToDTO(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBook(@RequestParam @NotNull @Valid @Min(1) Integer id) {
        if (bookService.deleteBook(id)) {
            return new ResponseEntity("deleted", HttpStatus.OK);
        }
        return noBookWasFind(id);
    }

    /** Add and modify authors and tags to a book that has this id. The id is not necessary to be in BookDTO **/
    @PutMapping(value = "/update-book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTOQuery> updateBook(@RequestBody @Valid BookDTO book, BindingResult bindingResult, @RequestParam Integer id) {
        if (bindingResult.hasErrors()) {
            throw new NullFieldInBookDTOFoundException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        if (bookService.queryBook(id) == null) {
            return noBookWasFind(id);
        }
        Book updateBook = bookService.updateBook(convert.fromDTOToBook(book), id);
        return new ResponseEntity<>(convert.fromBookToDTO(updateBook), HttpStatus.OK);
    }

    /** Update or remove authors and tags. The id is necessary to be part of BookDTOID body. **/
    @PutMapping(value = "/update-book-using-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTOQuery> updateBookWithId(@RequestBody @Valid BookDTOID book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NullFieldInBookDTOFoundException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        if (bookService.queryBook(book.getId()) == null) {
            return noBookWasFind(book.getId());
        }
        Book updateBook = bookService.updateBookUsingTagAndAuthorId(convert.fromDTOIDToBook(book));
        return new ResponseEntity<>(convert.fromBookToDTO(updateBook), HttpStatus.OK);
    }


    public ResponseEntity noBookWasFind(int id) {
        return new ResponseEntity(errorBuilder.getErrorMessageOnIncorrectBookIdException(id), HttpStatus.BAD_REQUEST);
    }
}





