package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.request.RentRequestDTO;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("request")
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private MessageBuilder messageBuilder;

    @PostMapping(value = "/register")
    public ResponseEntity<RentRequest> registerRentRequest(@RequestBody @Valid RentRequestDTO rentRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        rentRequestService.registerRentRequest(rentRequestDTO.getBookId(), rentRequestDTO.getUserId());
        return null;
    }


}
