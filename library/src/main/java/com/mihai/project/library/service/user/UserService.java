package com.mihai.project.library.service.user;

import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User queryUser(String username);

    User queryUserById(int id);

    List<User> queryAllUsers();

    boolean emailAlreadyExist(String email);

    boolean userAlreadyExist(String username);

    boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email);

    boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername);

    boolean deleteUser(String username);

    User updateUser(User userNewData, int userId);

    User performLogIn(String username, String password);

}
