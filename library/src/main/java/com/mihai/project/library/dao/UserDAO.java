package com.mihai.project.library.dao;

import com.mihai.project.library.entity.user.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface UserDAO {
    User addUser(User user);
    User queryUser(String username);
    List<User> queryAllUsers();
    boolean emailAlreadyExist(String email);
    boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email);
    boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername);
    boolean deleteUser(String username);
    User updateUser(User user, String username);
}
