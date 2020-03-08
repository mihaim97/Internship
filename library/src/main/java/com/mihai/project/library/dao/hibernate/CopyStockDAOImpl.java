package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.CopyStockDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.request.RentRequest;
import com.mihai.project.library.entity.stock.CopyStock;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.RentRequestStatus;
import com.mihai.project.library.util.enumeration.Status;
import com.mihai.project.library.util.enumeration.Flag;
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

    @Override
    public List<CopyStock> querySingleBookCopyByBookId(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Query<CopyStock> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_BOOK_COPY_BY_ID).setMaxResults(1);
        query.setParameter(MyTable.COPY_BOOK_BOOK_FK, bookId);
        query.setParameter(MyTable.COPY_BOOK_STATUS, Status.AV.toString());
        query.setParameter(MyTable.COPY_BOOK_FLAG, Flag.AV.toString());
        return query.getResultList();
    }

    @Override
    public List<CopyStock> queryAllCopy() {
        Session session = sessionFactory.getCurrentSession();
        Query<CopyStock> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_COPY);
        return query.getResultList();
    }

    @Override
    public boolean deleteCopy(CopyStock copyStock) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(copyStock);
        return true;
    }

    @Override
    public CopyStock updateCopy(CopyStock copyToUpdate, CopyStock newValue) {
        copyToUpdate.setFlag(newValue.getFlag());
        copyToUpdate.setStatus(newValue.getStatus());
        return copyToUpdate;
    }

    @Override
    public List<RentRequest> checkForRentRequestOnCurrentBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_QUERY_CHECK_IF_RENT_REQUEST_EXIST_WAC).setMaxResults(1);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_BOOK_FK, book);
        query.setParameter(MyTable.BOOK_RENT_REQUEST_STATUS, RentRequestStatus.WAC.toString());
        return query.getResultList();
    }

}
