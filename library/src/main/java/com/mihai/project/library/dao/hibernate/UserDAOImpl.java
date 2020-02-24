package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User addUser(User user){
        Session session = entityManager.unwrap(Session.class);
        if(queryUserByUsernameOnAdd(user.getUsername(), session) != null)
            return null;
        session.saveOrUpdate(user);
        return queryUserByUsernameOnAdd(user.getUsername(), session);
    }

    @Override
    public User queryUser(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return getSingleResultOrNullFromQuery(query);
    }

    @Override
    public List<User> queryAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_USERS);
        return query.getResultList();
    }

    @Override
    public boolean emailAlreadyExist(String email) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
        query.setParameter(MyTable.USER_EMAIL, email);
        return !(getSingleResultOrNullFromQuery(query) == null);
    }

    @Override
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.USER_EMAIL, email);
        query.setParameter(MyTable.USER_ID, currentUsername);
        if(getSingleResultOrNullFromQuery(query) != null)
            return true;
        return false;
    }

    @Override
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
        query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
        query.setParameter(MyTable.USER_ID, currentUsername);
        if(getSingleResultOrNullFromQuery(query) != null)
            return true;
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        Session session = entityManager.unwrap(Session.class);
        User user = queryUser(username);
        if(user != null){
            session.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(User user, String username) {
        Session session = entityManager.unwrap(Session.class);
        User newUser = queryUser(username);
        newUser.setEmail(user.getEmail());
        newUser.setEnable(user.getEnable());
        newUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        newUser.setRole(user.getRole());
        if(username.equals(user.getUsername())) {
            session.update(newUser);
            return queryUser(username);
        }else{
            session.delete(newUser);
            newUser.setUsername(user.getUsername());
            session.saveOrUpdate(newUser);
            return queryUser(user.getUsername());
        }
    }

    private User queryUserByUsernameOnAdd(String username, Session session) {
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return getSingleResultOrNullFromQuery(query);
    }

    private User getSingleResultOrNullFromQuery(Query<User> query){
        try{
            User user = query.getSingleResult();
            return user;
        }catch(NoResultException exc){
            return null;
        }
    }

}
