package com.mihai.project.library.service;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserServiceImpl implements  UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO){this.userDAO = userDAO;}

    @Override
    @Transactional
    public User addUser(User user){
        return userDAO.addUser(user);
    }

    @Override
    @Transactional
    public User queryUser(String username) {
        return userDAO.queryUser(username);
    }

    @Override
    @Transactional
    public List<User> queryAllUsers() {
        return userDAO.queryAllUsers();
    }

    @Override
    @Transactional
    public boolean emailAlreadyExist(String email) {
        return userDAO.emailAlreadyExist(email);
    }

    @Override
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        return  userDAO.emailAlreadyExistOnDifferentUser(currentUsername, email);
    }

    @Override
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        return userDAO.usernameAlreadyExistOnDifferentUser(currentUsername, newUsername);
    }

    @Override
    @Transactional
    public boolean deleteUser(String username) {
        return userDAO.deleteUser(username);
    }

    @Override
    @Transactional
    public User updateUser(User user, String username) {
        return userDAO.updateUser(user, username);
    }
}
