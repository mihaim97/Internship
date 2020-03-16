package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BannedUserDAO;
import com.mihai.project.library.entity.user.BannedUser;
import com.mihai.project.library.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BannedUserDAOImpl implements BannedUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void registerBannedUser(User user, int days) {
        Session session= sessionFactory.getCurrentSession();
        BannedUser bannedUser = new BannedUser();
        bannedUser.setUser(user);
        bannedUser.setDays(days);
        session.persist(bannedUser);
    }

}
