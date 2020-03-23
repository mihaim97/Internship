package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.statistics.TopBookDTO;
import com.mihai.project.library.dto.statistics.TopEmployeesDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.nativequery.TopBookRented;
import com.mihai.project.library.entity.nativequery.TopEmployees;
import com.mihai.project.library.service.statistics.StatisticsService;
import com.mihai.project.library.util.dtoentity.user.UserDTOEntityConverter;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private UserDTOEntityConverter converter;

    @GetMapping(value = "/top-books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopBookRented>> queryTopBooks(@Valid TopBookDTO topBookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        List<TopBookRented> bookRents = statisticsService.topBooksRented(topBookDTO);
        return new ResponseEntity(bookRents, HttpStatus.OK);
    }

    @GetMapping(value = "/top-employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopEmployees>> queryTopEmployees(@Valid TopEmployeesDTO topEmployeesDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        return new ResponseEntity<>(statisticsService.topEmployees(topEmployeesDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/employees-late", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTOOut>> queryAllEmployeesThatAreLate() {
        List<UserDTOOut> userDTOOuts = converter.fromUsersToDTOOut(statisticsService.allUserThatAreLate());
        return new ResponseEntity<>(userDTOOuts, HttpStatus.OK);
    }

}
