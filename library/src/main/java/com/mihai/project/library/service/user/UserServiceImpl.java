package com.mihai.project.library.service.user;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.message.MessageBuilder;
import com.mihai.project.library.util.enumeration.FieldType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(@Qualifier("UserDaoHibernate") UserDAO userDAO, MessageBuilder errorBuilder) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    @Transactional
    public User queryUser(String username) {
        return HibernateUtil.getUniqueResult(userDAO.queryUser(username));
    }

    @Override
    @Transactional
    public List<User> queryAllUsers() {
        return userDAO.queryAllUsers();
    }

    @Override
    @Transactional
    public boolean emailAlreadyExist(String email) {
        return checkForUsernameOrEmail(email, FieldType.EMAIL);
    }

    @Override
    @Transactional
    public boolean userAlreadyExist(String username) {
        return checkForUsernameOrEmail(username, FieldType.USERNAME);
    }

    @Override
    @Transactional
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        return checkForUsernameOrEmailOnDifferentUser(currentUsername, email, FieldType.EMAIL);
    }

    @Override
    @Transactional
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        return checkForUsernameOrEmailOnDifferentUser(currentUsername, newUsername, FieldType.USERNAME);
    }

    @Override
    @Transactional
    public boolean deleteUser(String username) {
        return userDAO.deleteUser(username);
    }

    @Override
    @Transactional
    public User updateUser(User userNewData, String username) {
        return userDAO.updateUser(username, userNewData);
    }

    private boolean checkForUsernameOrEmail(String value, FieldType operation) {
        if (operation == FieldType.EMAIL) {
            if (HibernateUtil.getUniqueResult(userDAO.emailAlreadyExist(value)) == null) {
                return false;
            }
        } else if (operation == FieldType.USERNAME) {
            if (HibernateUtil.getUniqueResult(userDAO.userAlreadyExist(value)) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForUsernameOrEmailOnDifferentUser(String currentUser, String value, FieldType operation) {
        if (operation == FieldType.EMAIL) {
            if (HibernateUtil.getUniqueResult(userDAO.emailAlreadyExistOnDifferentUser(currentUser, value)) == null) {
                return false;
            }
        } else if (operation == FieldType.USERNAME) {
            if (HibernateUtil.getUniqueResult(userDAO.usernameAlreadyExistOnDifferentUser(currentUser, value)) == null) {
                return false;
            }
        }
        return true;
    }

}
