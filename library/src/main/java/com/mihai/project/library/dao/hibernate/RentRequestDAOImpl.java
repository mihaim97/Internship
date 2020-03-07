package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.RentRequestDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
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

}
