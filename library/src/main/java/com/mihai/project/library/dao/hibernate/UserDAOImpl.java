package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDaoHibernateImplementation")
@Qualifier("UserDaoHibernate")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        return user;
    }

    @Override
    public List<User> queryUser(String username) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public List<User> queryAllUsers() {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_USERS);
        query.getResultList();
        return query.getResultList();
    }

    @Override
    public List<User> emailAlreadyExist(String email) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
        query.setParameter(MyTable.USER_EMAIL, email);
        return query.getResultList();
    }

    @Override
    public List<User> userAlreadyExist(String username) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public List<User> emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.USER_EMAIL, email);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public List<User> usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public boolean deleteUser(String username) {
        Session session = sessionFactory.openSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        User user = query.getSingleResult();
        System.out.println(user);
        session.delete(user);
        return true;
    }

    @Override
    public User updateUser(User user, User newUserData) {
        Session session = sessionFactory.openSession();
        user.setUsername(newUserData.getUsername());
        user.setRole(newUserData.getRole());
        user.setPassword(DigestUtils.md5Hex(newUserData.getPassword().getBytes()));
        user.setEmail(newUserData.getEmail());
        return user;
    }
}
