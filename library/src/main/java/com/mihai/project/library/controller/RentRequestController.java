package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.request.RentRequestDTO;
import com.mihai.project.library.dto.request.RentRequestDTOOut;
import com.mihai.project.library.dto.request.RentRequestDTOUpdate;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.util.dtoentity.request.RentRequestDTOEntityConverter;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("request")
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private RentRequestDTOEntityConverter convert;

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

    @GetMapping(value = "/user-request")
    public ResponseEntity<List<RentRequestDTOOut>> queryUserRequest(@RequestParam @Valid @Min(1) int userId) {
        List<RentRequest> rentRequests = rentRequestService.queryUserRentRequest(userId);
        return new ResponseEntity<>(convert.fromListRentRequestToListDtoOut(rentRequests), HttpStatus.OK);
    }

    @PatchMapping(value = "/accept")
    public ResponseEntity<Object> acceptOrCancelRequest(@RequestBody @Valid RentRequestDTOUpdate request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(messageBuilder.getErrorMessageFromResultBinding(bindingResult), HttpStatus.BAD_REQUEST);
        }
        rentRequestService.acceptOrCancelRentRequest(request.getRentRequestId(), request.getResponse());
        return null;
    }


}
