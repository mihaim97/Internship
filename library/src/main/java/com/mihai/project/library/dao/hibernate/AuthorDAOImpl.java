package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AuthorDaoHibernateImplementation")
@Qualifier("AuthorDaoHibernate")
public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public Number addAuthor(Author author) {
        return null;
    }

    @Override
    public List<Author> queryAuthors() {
        return null;
    }

    @Override
    public List<Author> querySingleAuthor(String name) {
        Transaction transaction = null;
        List<Author> author = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query<Author> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_AUTHOR_BY_NAME);
            query.setParameter(MyTable.AUTHOR_NAME, name);
            author = query.getResultList();
            transaction.commit();
        } catch (Exception exc) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return author;
    }
}
