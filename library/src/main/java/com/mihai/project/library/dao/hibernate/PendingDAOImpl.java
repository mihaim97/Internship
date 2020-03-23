package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.PendingDAO;
import com.mihai.project.library.entity.interntable.Pending;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.util.MyQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public Pending removePending(Pending pending) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(pending);
        return pending;
    }

    @Override
    public List<Pending> queryAllPending() {
        Session session = sessionFactory.getCurrentSession();
        Query<Pending> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_PENDING);
        return query.getResultList();
    }

    @Override
    public CopyStock queryCopyStockById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(CopyStock.class, id);
    }


}
