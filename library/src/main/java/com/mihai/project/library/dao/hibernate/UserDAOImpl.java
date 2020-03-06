package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository("UserDaoHibernateImplementation")
@Qualifier("UserDaoHibernate")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
        session.persist(user);
        return user;
    }

    @Override
    public List<User> queryUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query =  session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public User queryUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(User.class, id);
    }

    @Override
    public List<User> queryAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_USERS).getResultList();
    }

    @Override
    public List<User> emailAlreadyExist(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
        query.setParameter(MyTable.USER_EMAIL, email);
        return query.getResultList();
    }

    @Override
    public List<User> userAlreadyExist(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return query.getResultList();
    }

    @Override
    public List<User> emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.USER_EMAIL, email);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public List<User> usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
        query.setParameter(MyTable.USER_ID, currentUsername);
        return query.getResultList();
    }

    @Override
    public boolean deleteUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_DELETE_USER);
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
