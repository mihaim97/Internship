package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.entity.rent.BookRent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRentDAOImpl implements BookRentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BookRent registerBookRent(BookRent bookRent) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(bookRent);
        return null;
    }

    @Override
    public List<BookRent> queryAllBookRent() {
        return null;
    }
}
