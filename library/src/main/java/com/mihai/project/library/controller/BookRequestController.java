package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.request.BookRequestDTO;
import com.mihai.project.library.dto.request.BookRequestDTOConfirm;
import com.mihai.project.library.dto.request.BookRequestDTOOut;
import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.service.request.BookRequestService;
import com.mihai.project.library.util.dtoentity.request.BookRequestDTOEntityConverter;
import com.mihai.project.library.util.enumeration.BookRequestQuery;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book-request")
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private BookRequestDTOEntityConverter converter;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRequestDTOOut> registerBookRequest(@Valid @RequestBody BookRequestDTO bookRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        BookRequest bookRequest = bookRequestService.registerBookRequest(converter.fromBookRequestDTOToBookRequest(bookRequestDTO));
        return new ResponseEntity<>(converter.fromBookRequestToBookRequestDTOOut(bookRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookRequestDTOOut>> queryAllBookRequest() {
        List<BookRequest> bookRequests = bookRequestService.queryAllBookRequest(BookRequestQuery.ALL);
        return new ResponseEntity<>(converter.fromListBookRequestToListOut(bookRequests), HttpStatus.OK);
    }

    @GetMapping(value = "/list/for-confirmation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookRequestDTOOut>> queryAllBookRequestForConfirmation() {
        List<BookRequest> bookRequests = bookRequestService.queryAllBookRequest(BookRequestQuery.FOR_CONFIRMATION);
        return new ResponseEntity<>(converter.fromListBookRequestToListOut(bookRequests), HttpStatus.OK);
    }

    @GetMapping(value = "/list/to-buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookRequestDTOOut>> queryAllBookRequestToBuy() {
        List<BookRequest> bookRequests = bookRequestService.queryAllBookRequest(BookRequestQuery.TO_BUY);
        return new ResponseEntity<>(converter.fromListBookRequestToListOut(bookRequests), HttpStatus.OK);
    }

    @PutMapping(value = "/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRequestDTOOut> acceptOrDeclineRequest(@Valid @RequestBody BookRequestDTOConfirm answer,
                                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        BookRequest bookRequest = bookRequestService.acceptOrDeclineBookRequest(answer.getId(), answer.getAnswerStatus());
        return new ResponseEntity<>(converter.fromBookRequestToBookRequestDTOOut(bookRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookRequestDTOOut> removeBookRequest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
