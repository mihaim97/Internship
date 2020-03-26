package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.ResultBindingValidationException;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOAdd;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.filter.AuthenticationWrapperServletRequest;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.dtoentity.user.UserDTOEntityConverter;
import com.mihai.project.library.util.message.user.UserMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOEntityConverter convert;

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private UserMessageBuilder userMessageBuilder;

    public UserController(){}

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody @Valid UserDTOAdd user, BindingResult bindingResult, @ApiIgnore AuthenticationWrapperServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        if (userService.emailAlreadyExist(user.getEmail())) {
            return emailExist(user.getEmail());
        }
        if (userService.userAlreadyExist(user.getUsername())) {
            return userExist(user.getUsername());
        }
        User userReturned = userService.addUser(convert.fromDTOAddToUser(user));
        return ResponseEntity.ok(convert.fromUserToUserDTOOut(userReturned));
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestParam String username) {
        if (!userService.deleteUser(username)) {
            return noUserWithId(username);
        }
        return new ResponseEntity(userMessageBuilder.getMessageOnUserSuccessfullyDeleted(username), HttpStatus.OK);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTOOut>> queryAllUsers() {
        return new ResponseEntity<>(convert.fromUsersToDTOOut(userService.queryAllUsers()), HttpStatus.OK);
    }

    @GetMapping(path = "/single-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryUser(@RequestParam String username) {
        User user = userService.queryUser(username);
        System.out.println(user);
        if (user != null) {
            return new ResponseEntity(convert.fromUserToUserDTOOut(user), HttpStatus.OK);
        } else {
            return noUserWithId(username);
        }
    }

    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTOOut> updateUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResultBindingValidationException(messageBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        User userReturned = userService.updateUser(convert.fromDTOToUser(user), user.getId());
        return new ResponseEntity<>(convert.fromUserToUserDTOOut(userReturned), HttpStatus.OK);
    }

    public ResponseEntity userExist(String user) {
        return new ResponseEntity(userMessageBuilder.getMessageOnUserExistException(user), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity emailExist(String mail) {
        return new ResponseEntity(userMessageBuilder.getMessageOnEmailAlreadyExist(mail), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity noUserWithId(String username) {
        return new ResponseEntity(userMessageBuilder.getMessageOnNoSuchUserToDeleteOrUpdate(username), HttpStatus.BAD_REQUEST);
    }

}
