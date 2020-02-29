package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("AuthorDaoHibernateImplementation")
@Qualifier("AuthorDaoHibernate")
public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Number addAuthor(Author author) {
        return null;
    }

    @Override
    public List<Author> queryAuthors() {
        return null;
    }

    @Override
    public List<Author> querySingleAuthorForBookValidation(String name) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_AUTHOR_BY_NAME);
        query.setParameter(MyTable.AUTHOR_NAME, name);
        return query.getResultList();
    }
}
