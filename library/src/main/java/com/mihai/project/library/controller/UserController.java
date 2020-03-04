package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.IncorrectUserException;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.user.UserService;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.dtoentity.user.UserDTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTOEntityConverter convert;

    @Autowired
    private MyErrorBuilder errorBuilder;

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IncorrectUserException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        if (userService.emailAlreadyExist(user.getEmail())) {
            return emailExist(user);
        }
        if (userService.userAlreadyExist(user.getUsername())) {
            return userExist(user);
        }
        User userReturned = userService.addUser(convert.fromDTOToUser(user));
        return new ResponseEntity(convert.fromUserToUserDTOOut(userReturned), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestParam String username) {
        if (!userService.deleteUser(username)) {
            return noUserWithId(username);
        }
        return new ResponseEntity(errorBuilder.getMessageOnUserSuccessfullyDeleted(username), HttpStatus.OK);
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

    @PutMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult, @RequestParam String username) {
        if (bindingResult.hasErrors()) {
            throw new IncorrectUserException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        if (userService.usernameAlreadyExistOnDifferentUser(username, user.getUsername())) {
            return userExist(user);
        }
        if (userService.emailAlreadyExistOnDifferentUser(username, user.getEmail())) {
            return emailExist(user);
        }
        User userReturned = userService.updateUser(convert.fromDTOToUser(user), username);
        return new ResponseEntity<>(convert.fromUserToUserDTOOut(userReturned), HttpStatus.OK);
    }

    public ResponseEntity userExist(UserDTO user) {
        return new ResponseEntity(errorBuilder.getErrorMessageOnUserExistException(user.getUsername()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity emailExist(UserDTO user) {
        return new ResponseEntity(errorBuilder.getErrorMessageOnEmailAlreadyExist(user.getEmail()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity noUserWithId(String username) {
        return new ResponseEntity(errorBuilder.getErrorMessageOnNoSuchUserToDeleteOrUpdate(username), HttpStatus.BAD_REQUEST);
    }

}
