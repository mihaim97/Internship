package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public CopyStock queryCopyStock(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(CopyStock.class, id);
    }

    @Override
    public List<CopyStock> queryAllBookCopy(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CopyStock> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOK_COPY);
        query.setParameter(MyTable.COPY_BOOK_BOOK_ID, bookId);
        return query.getResultList();
    }
}
