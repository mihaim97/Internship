package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.stock.CopyStockDTO;
import com.mihai.project.library.dto.stock.CopyStockDTOOut;
import com.mihai.project.library.dto.stock.CopyStockDTOUpdate;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.service.book.BookService;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.dtoentity.stock.CopyStockDTOEntityConverter;
import com.mihai.project.library.util.message.book.BookMessageBuilder;
import com.mihai.project.library.util.message.stock.CopyStockMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
    private MessageBuilder messageBuilder;

    @Autowired
    private CopyStockMessageBuilder copyMessageBuilder;

    @Autowired
    private BookMessageBuilder bookMessageBuilder;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCopyBook(@RequestBody @Valid CopyStockDTO copyStockDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        Book book = bookService.queryBook(copyStockDTO.getBookId());
        if (book != null) {
            CopyStock copyStock = copyStockService.addSingleCopy(book, copyStockDTO.getFlag(), copyStockDTO.getStatus());
            return new ResponseEntity<>(convert.fromCopyStockToDto(copyStock), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookMessageBuilder.getMessageOnIncorrectBookId(copyStockDTO.getBookId()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "query-copy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryCopyBook(@RequestParam @Valid @Min(1) int code) {
        CopyStock copyStock = copyStockService.queryCopyStock(code);
        if (copyStock != null) {
            return new ResponseEntity<>(convert.fromCopyStockToDto(copyStock), HttpStatus.OK);
        }
        return new ResponseEntity<>(copyMessageBuilder.getMessageOnNoCopyWithCode(code), HttpStatus.BAD_REQUEST);
    }

    /** All copy of a book **/
    @GetMapping(value = "book-all-copy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CopyStockDTOOut> queryAllCopyOfSpecificBook(@RequestParam @Valid @Min(1) int bookId){
        return convert.fromCopyStockListToDtoOutList(copyStockService.queryAllBookCopy(bookId));
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CopyStockDTOOut> queryAllCopy(){
        return convert.fromCopyStockListToDtoOutList(copyStockService.queryAllCopy());
    }

    @DeleteMapping(value = "/delete" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCopy(@RequestParam @Valid @Min(1) int copyCode){
        if(copyStockService.deleteCopy(copyCode)){
            return new ResponseEntity<>(copyMessageBuilder.getMessageOnCopySuccessfullyDeleted(copyCode), HttpStatus.OK);
        }
        return new ResponseEntity<>(copyMessageBuilder.getMessageOnNoCopyWithCode(copyCode), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCopy(@RequestBody @Valid CopyStockDTOUpdate copyStockDTOUpdate, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        CopyStock copyStock = copyStockService.updateCopy(convert.fromCopyStockUpdateToCopyStock(copyStockDTOUpdate));
        if(copyStock != null){
            return new ResponseEntity<>(convert.fromCopyStockToDto(copyStock), HttpStatus.OK);
        }
        return new ResponseEntity<>(copyMessageBuilder.getMessageOnNoCopyWithCode(copyStockDTOUpdate.getCode()), HttpStatus.BAD_REQUEST);
    }
}
