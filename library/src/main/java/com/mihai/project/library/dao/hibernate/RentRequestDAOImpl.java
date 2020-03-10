package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.RentRequestDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.RentStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RentRequestDAOImpl implements RentRequestDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RentRequest registerRentRequest(RentRequest rentRequest, Book book, User user) {
        Session session = sessionFactory.getCurrentSession();
        rentRequest.setBookId(book);
        rentRequest.setUser(user);
        rentRequest.setStatus(RentRequestStatus.WAC.toString());
        rentRequest.setDateRequest(new Date());
        session.persist(rentRequest);
        return rentRequest;
    }

    @Override
    public List<RentRequest> checkIfUserHasARequestForCurrentBook(Book book, User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<RentRequest> query = session.createQuery(MyQuery.HIBERNATE_QUERY_CHECK_IF_RENT_REQUEST_EXIST);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_STATUS, RentRequestStatus.WAC.toString());
        query.setParameter(MyTable.BOOK_RENT_REQUEST_STATUS2, RentRequestStatus.WFC.toString());
        query.setParameter(MyTable.BOOK_RENT_REQUEST_BOOK_FK, book);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_USER_FK, user);
        return query.getResultList();
    }

    @Override
    public List<BookRent> checkIfUserAlreadyHasAvailableBookRent(Book book, User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_BOOK_RENT_BY_USER_BOOK_ID);
        query.setParameter(MyTable.BOOK_RENT_BOOK_FK, book);
        query.setParameter(MyTable.BOOK_RENT_USER_FK, user);
        query.setParameter(MyTable.BOOK_RENT_STATUS, RentStatus.ON.toString());
        query.setParameter(MyTable.BOOK_RENT_STATUS2, RentStatus.LA.toString());
        return query.getResultList();
    }

    @Override
    public List<RentRequest> checkForExistingRequest(Book book) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_EXISTING_BOOK_RENT_REQUEST).setMaxResults(1);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_BOOK_FK, book);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_STATUS, RentRequestStatus.WAC.toString());
        return query.getResultList();
    }

    @Override
    public List<RentRequest> queryUserRentRequest(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_USER_RENT_REQUEST);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_BOOK_FK, userId);
        query.setParameter(MyTable.BOOK_RENT_STATUS, RentRequestStatus.WFC.toString());
        return query.getResultList();
    }

    @Override
    public RentRequest querySingleRentRequestById(int rentRequestId) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(RentRequest.class, rentRequestId);
    }

    @Override
    public List<RentRequest> queryRentRequestWithStatusWFC(int rentRequestId) {
        Session session = sessionFactory.getCurrentSession();
        Query<RentRequest> query = session.createQuery(MyQuery.HIBERNATE_QUERY_RENT_REQUEST_WFC);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_ID, rentRequestId);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_STATUS, RentRequestStatus.WFC.toString());
        return query.getResultList();
    }

    @Override
    public BookRent registerBookRentAfterUserAccept(BookRent bookRent) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(bookRent);
        return bookRent;
    }

}
