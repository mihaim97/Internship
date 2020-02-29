package com.mihai.project.library.dao;

import com.mihai.project.library.entity.user.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserDAO {
    User addUser(User user);
    List<User> queryUser(String username);
    List<User> queryAllUsers();
    List<User> emailAlreadyExist(String email);
    List<User> userAlreadyExist(String username);
    List<User> emailAlreadyExistOnDifferentUser(String currentUsername, String email);
    List<User> usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername);
    boolean deleteUser(String user);
    User updateUser(String username, User newUserData);
}
