package com.mihai.project.library.controller;

import com.mihai.project.library.contralleradvice.exception.IncorrectUserException;
import com.mihai.project.library.contralleradvice.exception.NoSuchUserException;
import com.mihai.project.library.dto.user.UserDTO;
import com.mihai.project.library.dto.user.UserDTOOut;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.service.UserService;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.dtoentity.UserDTOEntityConverter;
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
    public ResponseEntity<UserDTOOut> addUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IncorrectUserException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        User userReturned = userService.addUser(convert.fromDTOToUser(user));
        return new ResponseEntity<>(convert.fromUserToUserDTOOut(userReturned), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        if (!userService.deleteUser(username))
            throw new NoSuchUserException(errorBuilder.getErrorMessageOnNoSuchUserToDeleteOrUpdate(username));
        return new ResponseEntity<>(errorBuilder.getErrorMessageOnUserSuccessfullyDeleted(username), HttpStatus.OK);
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTOOut>> queryAllUsers() {
        return new ResponseEntity<>(convert.fromUsersToDTOOut(userService.queryAllUsers()), HttpStatus.OK);
    }

    @GetMapping(path = "/single-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTOOut> queryUser(@RequestParam String username) {
        return new ResponseEntity<>(convert.fromUserToUserDTOOut(userService.queryUser(username)), HttpStatus.OK);
    }

    @PutMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTOOut> updateUser(@RequestBody @Valid UserDTO user, BindingResult bindingResult, @RequestParam String username) {
        if (bindingResult.hasErrors()) {
            throw new IncorrectUserException(errorBuilder.getErrorMessageFromResultBinding(bindingResult));
        }
        User userReturned = userService.updateUser(convert.fromDTOToUser(user), username);
        return new ResponseEntity<>(convert.fromUserToUserDTOOut(userReturned), HttpStatus.OK);
    }
}
