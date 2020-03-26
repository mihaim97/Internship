package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.request.RentRequestDTO;
import com.mihai.project.library.dto.request.RentRequestDTOOut;
import com.mihai.project.library.dto.request.RentRequestDTOUpdate;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.filter.AuthenticationWrapperServletRequest;
import com.mihai.project.library.service.request.RentRequestService;
import com.mihai.project.library.util.dtoentity.request.RentRequestDTOEntityConverter;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/request")
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private RentRequestDTOEntityConverter convert;

    @Autowired
    private MessageBuilder messageBuilder;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentRequestDTOOut> registerRentRequest(@RequestBody @Valid RentRequestDTO rentRequestDTO, BindingResult bindingResult,
                                                                 @ApiIgnore AuthenticationWrapperServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        RentRequest rentRequest = rentRequestService.registerRentRequest(rentRequestDTO.getBookId(), request.getAuthenticateUser());
        return ResponseEntity.ok(convert.fromRentRequestToDtoOut(rentRequest));
    }

    @GetMapping(value = "/user-request")
    public ResponseEntity<List<RentRequestDTOOut>> queryUserRequest(@ApiIgnore AuthenticationWrapperServletRequest request) {
        List<RentRequest> rentRequests = rentRequestService.queryUserRentRequest(request.getAuthenticateUser().getId());
        return ResponseEntity.ok(convert.fromListRentRequestToListDtoOut(rentRequests));
    }

    @PatchMapping(value = "/accept")
    public ResponseEntity<Object> acceptOrCancelRequest(@RequestBody @Valid RentRequestDTOUpdate rentDto, BindingResult bindingResult,
                                                        @ApiIgnore AuthenticationWrapperServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(messageBuilder.getErrorMessageFromResultBinding(bindingResult), HttpStatus.BAD_REQUEST);
        }
        RentRequest rentRequest = rentRequestService.acceptOrCancelRentRequest(rentDto.getRentRequestId(), rentDto.getResponse(), request.getAuthenticateUser());
        return ResponseEntity.ok(convert.fromRentRequestToDtoOut(rentRequest));
    }


}
