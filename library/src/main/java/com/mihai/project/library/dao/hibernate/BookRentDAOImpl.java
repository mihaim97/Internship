package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.rent.BookRent;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.RentStatus;
import com.mihai.project.library.util.enumeration.Status;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class BookRentDAOImpl implements BookRentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BookRent registerBookRent(BookRent bookRent, CopyStock copyStock, User user, int period) {
        Session session = sessionFactory.getCurrentSession();
        bookRent.setBook(copyStock.getBookId());
        bookRent.setCopy(copyStock);
        bookRent.setUser(user);
        bookRent.setStatus(RentStatus.ON.toString());
        bookRent.setDateRent(new Date());
        bookRent.setEndDateRent(DateUtils.addMonths(new Date(), period));
        session.persist(bookRent);
        return bookRent;
    }

    @Override
    public List<BookRent> checkIfUserAlreadyHasARentForCurrentBook(Book book, User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_BOOK_RENT_BY_USER_BOOK_ID);
        query.setParameter(MyTable.BOOK_RENT_BOOK_FK, book);
        query.setParameter(MyTable.BOOK_RENT_USER_FK, user);
        query.setParameter(MyTable.BOOK_RENT_STATUS, Status.AV.toString());
        query.setParameter(MyTable.BOOK_RENT_STATUS2, RentStatus.LA.toString());
        return query.getResultList();
    }

    @Override
    public BookRent returnARentedBook(BookRent bookRent, float note) {
        Session session = sessionFactory.getCurrentSession();
        bookRent.setNote(note);
        bookRent.setStatus(RentStatus.RE.toString());
        bookRent.getCopy().setStatus(Status.AV.toString());
        return bookRent;
    }

    @Override
    public BookRent queryBookRent(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(BookRent.class, id);
    }

    @Override
    public List<BookRent> queryAllBookRent() {
        return null;
    }
}
