package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookTagDAO;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookTagDAOImpl implements BookTagDAO {

    @Override
    public List<BookTag> querySingleTag(String tagName) {
        Transaction transaction = null;
        List<BookTag> tag = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<BookTag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_TAG);
            query.setParameter(MyTable.TAG_FIELD, tagName);
            tag = query.getResultList();
            transaction.commit();
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return tag;
    }
}
