package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("UserDaoHibernateImplementation")
@Qualifier("UserDaoHibernate")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User addUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> queryUser(String username) {
        Query query =  entityManager.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public List<User> queryAllUsers() {
        return entityManager.createQuery(MyQuery.HIBERNATE_QUERY_ALL_USERS).getResultList();
    }

    @Override
    public List<User> emailAlreadyExist(String email) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
        query.setParameter(MyTable.USER_EMAIL, email);
        return query.getResultList();
    }

    @Override
    public List<User> userAlreadyExist(String username) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public List<User> emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.USER_EMAIL, email);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public List<User> usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public boolean deleteUser(String username) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_DELETE_USER);
        query.setParameter(MyTable.USER_ID, username);
        return query.executeUpdate() == 1;
    }

    @Override
    public User updateUser(String username, User newUserData) {
        User user = queryUser(username).get(0);
        user.setUsername(newUserData.getUsername());
        user.setPassword(DigestUtils.md5Hex(newUserData.getPassword().getBytes()));
        user.setEmail(newUserData.getEmail());
        user.setRole(newUserData.getRole());
        return user;
    }
}
