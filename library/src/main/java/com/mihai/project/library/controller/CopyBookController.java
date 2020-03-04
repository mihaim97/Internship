package com.mihai.project.library.controller;

import com.mihai.project.library.dto.stock.CopyStockDTO;
import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.service.book.BookService;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.dtoentity.stock.CopyStockDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("book-copy")
public class CopyBookController {

    @Autowired
    private CopyStockService copyStockService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CopyStockDTOEntityConverter convert;

    @Autowired
    private MyErrorBuilder errorBuilder;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCopyBook(@RequestBody @Valid CopyStockDTO copyStockDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity(errorBuilder.getErrorMessageFromResultBinding(bindingResult), HttpStatus.BAD_REQUEST);
        }
        Book book = bookService.queryBook(copyStockDTO.getBookId());
        if (book != null) {
            CopyStock copyStock = copyStockService.addSingleCopy(book, copyStockDTO.getFlag(), copyStockDTO.getStatus());
            return new ResponseEntity<>(convert.fromCopyStockToDto(copyStock), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "query-copy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryCopyBook(@RequestParam int id) {
        CopyStock copyStock = copyStockService.queryCopyStock(id);
        if (copyStock != null) {
            return new ResponseEntity<>(convert.fromCopyStockToDto(copyStock), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /** All copy of a book **/
    @GetMapping(value = "book-all-copy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CopyStockDTOOut> queryAllCopyOfSpecificBook(@RequestParam int bookId){
        return convert.fromCopyStockListToDtoOutList(copyStockService.queryAllBookCopy(bookId));
    }

}
