package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.entity.user.User;
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

   // @Autowired
   // private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User addUser(User user){
        Transaction transaction = null;
        User userToReturn = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            if(queryUserByUsernameOnAdd(user.getUsername(), session) != null){
                transaction.commit();
                return null;
            }
            session.saveOrUpdate(user);
            userToReturn = queryUserByUsernameOnAdd(user.getUsername(), session);
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return userToReturn;
    }

    @Override
    public User queryUser(String username) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
            query.setParameter(MyTable.USER_ID, username);
            User user = getSingleResultOrNullFromQuery(query);
            if(user != null){
                transaction.commit();
                return user;
            }
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = sessionFactory.openSession()) {
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
    public boolean emailAlreadyExist(String email) {
        Transaction transaction = null;
        boolean emailExist = false;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_EMAIL);
            query.setParameter(MyTable.USER_EMAIL, email);
            User user = getSingleResultOrNullFromQuery(query);
            if(user != null){
                emailExist = true;
            }
            transaction.commit();
        }catch(Exception exc){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return emailExist;
    }

    @Override
    public boolean emailAlreadyExistOnDifferentUser(String currentUsername, String email) {
        Transaction transaction = null;
        boolean emailExistOnDifferentUser = false;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER);
            query.setParameter(MyTable.USER_EMAIL, email);
            query.setParameter(MyTable.USER_ID, currentUsername);
            User user = getSingleResultOrNullFromQuery(query);
            if(user != null){
                emailExistOnDifferentUser = true;
            }
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return  emailExistOnDifferentUser;
    }

    @Override
    public boolean usernameAlreadyExistOnDifferentUser(String currentUsername, String newUsername) {
        Transaction transaction = null;
        boolean usernameExistOnDifferentUser = false;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER);
            query.setParameter(MyTable.HIBERNATE_USER_NEW, newUsername);
            query.setParameter(MyTable.USER_ID, currentUsername);
            User user = getSingleResultOrNullFromQuery(query);
            if(user != null){
                usernameExistOnDifferentUser = true;
            }
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return usernameExistOnDifferentUser;
    }

    @Override
    public boolean deleteUser(String username) {
        User user = queryUser(username);
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            if(user != null){
                session.delete(user);
                transaction.commit();
                return true;
            }
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public User updateUser(User user, String username) {
        User oldUser = queryUser(username);
        Transaction transaction = null;
        boolean hasNewName = false;
        if(oldUser == null){
            return null;
        }
        populateUserDataOnUpdate(oldUser, user);
        try(Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            if(hasUserNewName(oldUser, user)){ /** Username is primary key, if has new name we need to delete and after insert**/
                session.update(oldUser);
            }else{
                session.delete(oldUser);
                oldUser.setUsername(user.getUsername());
                session.saveOrUpdate(oldUser);
                hasNewName = true;
            }
            transaction.commit();
        }catch (Exception exc){
            if(transaction != null){
                transaction.rollback();
            }
        }
        if(hasNewName){
            return queryUser(user.getUsername());
        }
        return queryUser(username);
    }

    private User queryUserByUsernameOnAdd(String username, Session session) {
        Query<User> query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_BY_USERNAME);
        query.setParameter(MyTable.USER_ID, username);
        return getSingleResultOrNullFromQuery(query);
    }

    private User getSingleResultOrNullFromQuery(Query<User> query){
        try{
            return query.getSingleResult();
        }catch(NoResultException exc){
            return null;
        }
    }

    private void populateUserDataOnUpdate(User oldUser, User user){
        oldUser.setEmail(user.getEmail());
        oldUser.setEnable(user.getEnable());
        oldUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        oldUser.setRole(user.getRole());
    }

    private boolean hasUserNewName(User oldUser, User user){
        return oldUser.getUsername().equals(user.getUsername());
    }

}
