package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.stock.CopyStock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CopyStockDAOImpl implements CopyStockDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CopyStock addBookCopy(CopyStock copyStock) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(copyStock);
        return copyStock;
    }
}
