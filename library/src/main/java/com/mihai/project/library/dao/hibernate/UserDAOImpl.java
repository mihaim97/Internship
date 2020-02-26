package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.config.Hibernate;
import com.mihai.project.library.contralleradvice.exception.NoUniqueUser;
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

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository("UserDaoHibernateImplementation")
@Qualifier("UserDaoHibernate")
public class UserDAOImpl implements UserDAO {

    @Override
    public User addUser(User user){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.save(user);
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public List<User> queryUser(String username) {
        Transaction transaction = null;
        List<User> user = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
            query.setParameter(MyTable.USER_ID, username);
            user = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public List<User> queryAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_USERS);
            users = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public List<User> emailAlreadyExist(String email) {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
            query.setParameter(MyTable.USER_EMAIL, email);
            users = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public List<User> userAlreadyExist(String username) {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
            query.setParameter(MyTable.USER_ID, username);
            users = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public List<User> emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
            query.setParameter(MyTable.USER_EMAIL, email);
            query.setParameter(MyTable.USER_ID, currentUsername);
            users = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public List<User> usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
            query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
            query.setParameter(MyTable.USER_ID, currentUsername);
            users = query.getResultList();
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public boolean deleteUser(User username) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(username);
            transaction.commit();
            return  true;
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public User updateUser(User user, User newUserData) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.contains(user));
            transaction = session.getTransaction();
            transaction.begin();
            user.setUsername(newUserData.getUsername());
            user.setRole(newUserData.getRole());
            user.setPassword(DigestUtils.md5Hex(newUserData.getPassword().getBytes()));
            user.setEmail(newUserData.getEmail());
            session.update(user);
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return user;
    }
}
