package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookRequestDAO;
import com.mihai.project.library.entity.request.BookRequest;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.BookRequestStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRequestDAOImpl implements BookRequestDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BookRequest registerBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(bookRequest);
        return bookRequest;
    }

    @Override
    public List<BookRequest> queryAllBookRequest() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOK_REQUEST);
        return query.getResultList();
    }

    @Override
    public List<BookRequest> queryAllBookRequestForConfirmation() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOK_REQUEST_WITH_STATUS);
        query.setParameter(MyTable.BOOK_REQUEST_STATUS, BookRequestStatus.PE.toString());
        return query.getResultList();

    }

    @Override
    public List<BookRequest> queryAllBookRequestToBuy() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOK_REQUEST_WITH_STATUS);
        query.setParameter(MyTable.BOOK_REQUEST_STATUS, BookRequestStatus.AC.toString());
        return query.getResultList();
    }

    @Override
    public BookRequest queryBookRequestById(int bookRequestId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(BookRequest.class, bookRequestId);
    }
}
