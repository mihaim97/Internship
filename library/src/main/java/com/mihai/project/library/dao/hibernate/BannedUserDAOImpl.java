package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BannedUserDAO;
import com.mihai.project.library.entity.user.BannedUser;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class BannedUserDAOImpl implements BannedUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void registerBannedUser(User user, int days) {
        Session session= sessionFactory.getCurrentSession();
        BannedUser bannedUser = new BannedUser();
        bannedUser.setUser(user);
        bannedUser.setEndDate(DateUtils.addDays(new Date(), days));
        session.persist(bannedUser);
    }

    @Override
    public List<BannedUser> checkIfUserIsBanned(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<BannedUser> query = session.createQuery(MyQuery.HIBERNATE_QUERY_BANNED_USER_BY_USER_ID);
        query.setParameter(MyTable.BANNED_USER_USER_ID, user);
        return query.getResultList();
    }

    @Override
    public void checkIfBannedExpiredAndDelete() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_DELETE_BANNED_USER);
        query.executeUpdate();
    }

}
