package com.mihai.project.library.dao;

import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface UserDAO {
    public User addUser(User user);
    public User queryUser(String username);
    public List<User> queryAllUsers();
    public boolean emailAlreadyExist(String email);
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email);
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername);
    public boolean deleteUser(String username);
    public User updateUser(User user, String username);
}
