package com.mihai.project.library.service;

import com.mihai.project.library.contralleradvice.exception.EmailAlreadyExistException;
import com.mihai.project.library.contralleradvice.exception.NoSuchUserException;
import com.mihai.project.library.contralleradvice.exception.NoUniqueUser;
import com.mihai.project.library.contralleradvice.exception.UserExistException;
import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyErrorBuilder;
import com.mihai.project.library.util.enumeration.FieldType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private MyErrorBuilder errorBuilder;

    public UserServiceImpl(@Qualifier("UserDaoHibernate") UserDAO userDAO, MyErrorBuilder errorBuilder) {
        this.userDAO = userDAO;
        this.errorBuilder = errorBuilder;
    }

    @Override
    //@Transactional
    public User addUser(User user) {
        if (emailAlreadyExist(user.getEmail())) {
            throw new EmailAlreadyExistException(errorBuilder.getErrorMessageOnEmailAlreadyExist(user.getEmail()));
        }
        if (userAlreadyExist(user.getUsername())) {
            throw new UserExistException(errorBuilder.getErrorMessageOnUserExistException(user.getUsername()));
        }
        return userDAO.addUser(user);
    }

    @Override
    //@Transactional
    public User queryUser(String username) {
        try {
            User user = getUniqueResult(userDAO.queryUser(username));
            if (user == null) {
                throw new NoSuchUserException(errorBuilder.getErrorMessageOnNoSuchUserToDeleteOrUpdate(username));
            }
            return user;
        } catch (NoUniqueUser exc) {
            throw new NoSuchUserException(errorBuilder.getErrorMessageOnNoSuchUserToDeleteOrUpdate(username));
        }
    }

    @Override
    //@Transactional
    public List<User> queryAllUsers() {
        return userDAO.queryAllUsers();
    }

    @Override
    public boolean emailAlreadyExist(String email) {
        return checkForUsernameOrEmail(email, FieldType.EMAIL);
    }

    @Override
    public boolean userAlreadyExist(String username) {
        return checkForUsernameOrEmail(username, FieldType.USERNAME);
    }

    @Override
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        return checkForUsernameOrEmailOnDifferentUser(currentUsername, email, FieldType.EMAIL);
    }

    @Override
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        return checkForUsernameOrEmailOnDifferentUser(currentUsername, newUsername, FieldType.USERNAME);
    }

    @Override
    public boolean deleteUser(String username) {
        return userDAO.deleteUser(queryUser(username));
    }

    @Override
    @Transactional
    public User updateUser(User userNewData, String username) {
        User user = queryUser(username);
        if (usernameAlreadyExistOnDifferentUser(user.getUsername(), userNewData.getUsername())) {
            throw new UserExistException(errorBuilder.getErrorMessageOnUserExistException(userNewData.getUsername()));
        }
        if (emailAlreadyExistOnDifferentUser(user.getUsername(), userNewData.getEmail())) {
            throw new EmailAlreadyExistException(errorBuilder.getErrorMessageOnEmailAlreadyExist(userNewData.getEmail()));
        }
        return userDAO.updateUser(user, userNewData);
    }

    public static <T> T getUniqueResult(List<T> resultList) throws NoUniqueUser {
        if (resultList == null || resultList.isEmpty()) {
            return null;
        }
        if (resultList.size() > 1) {
            throw new NoUniqueUser("Too many results");
        }
        return (T) resultList.get(0);
    }

    private boolean checkForUsernameOrEmail(String value, FieldType operation) {
        try {
            if (operation == FieldType.EMAIL) {
                if (getUniqueResult(userDAO.emailAlreadyExist(value)) == null) {
                    return false;
                }
            } else if (operation == FieldType.USERNAME) {
                if (getUniqueResult(userDAO.userAlreadyExist(value)) == null) {
                    return false;
                }
            }
        } catch (NoUniqueUser exc) {
            return true;
        }
        return true;
    }

    private boolean checkForUsernameOrEmailOnDifferentUser(String currentUser, String value, FieldType operation) {
        try {
            if (operation == FieldType.EMAIL) {
                if (getUniqueResult(userDAO.emailAlreadyExistOnDifferentUser(currentUser, value)) == null) {
                    return false;
                }
            } else if (operation == FieldType.USERNAME) {
                if (getUniqueResult(userDAO.usernameAlreadyExistOnDifferentUser(currentUser, value)) == null) {
                    return false;
                }
            }
        } catch (NoUniqueUser exc) {
            return true;
        }
        return true;
    }

}
