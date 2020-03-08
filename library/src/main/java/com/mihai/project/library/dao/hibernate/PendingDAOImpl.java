package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.PendingDAO;
import com.mihai.project.library.entity.interntable.Pending;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PendingDAOImpl implements PendingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Pending registerPending(Pending pending) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(pending);
        return pending;
    }
}
