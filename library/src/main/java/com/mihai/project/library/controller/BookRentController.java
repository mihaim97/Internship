package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.rent.BookRentDTO;
import com.mihai.project.library.dto.rent.BookRentDTOOut;
import com.mihai.project.library.dto.rent.BookRentReturnedDTO;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.filter.AuthenticationWrapperServletRequest;
import com.mihai.project.library.service.rent.BookRentService;
import com.mihai.project.library.util.dtoentity.rent.BookRentDTOEntityConverter;
import com.mihai.project.library.util.message.ExceptionMessage;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("rent-book")
public class BookRentController {

    @Autowired
    private BookRentService bookRentService;

    @Autowired
    private BookRentDTOEntityConverter convert;

    @Autowired
    private MessageBuilder messageBuilder;


    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRentDTOOut> registerRentBook(@RequestBody @Valid BookRentDTO bookRentDTO,
                                                           BindingResult bindingResult, @ApiIgnore AuthenticationWrapperServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        BookRent bookRent = bookRentService.registerBookRent(bookRentDTO.getBookToRentId(), request.getAuthenticateUser(), bookRentDTO.getPeriod());
        return new ResponseEntity(convert.fromBookRentToDtoOut(bookRent), HttpStatus.OK);
    }

    @PutMapping(value = "/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> returnBookRent(@RequestBody @Valid BookRentReturnedDTO bookRentReturnedDTO,
                                                 BindingResult bindingResult, @ApiIgnore AuthenticationWrapperServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        BookRent bookRent = bookRentService.returnARentedBook(bookRentReturnedDTO.getRentId(), bookRentReturnedDTO.getNote(), request.getAuthenticateUser());
        if (bookRent == null) {
            return new ResponseEntity(messageBuilder.asJSON(ExceptionMessage.BOOK_RENT_FAIL), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(convert.fromBookRentToDtoOut(bookRent), HttpStatus.OK);
    }

    @PutMapping(value = "/mark-late", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookRentDTOOut>> markAsLate() {
        List<BookRent> bookRentDTO = bookRentService.markBookRentAsLateIfExist();
        return new ResponseEntity<>(convert.fromBookRentListToDtoOutList(bookRentDTO), HttpStatus.OK);
    }

    @PutMapping(value = "/extend-rent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRentDTOOut> extendRent(@RequestParam @Valid @Min(1) int rentId) {
        BookRent bookRent = bookRentService.extendRent(rentId);
        return new ResponseEntity<BookRentDTOOut>(convert.fromBookRentToDtoOut(bookRent), HttpStatus.OK);
    }


}
