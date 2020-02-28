package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;

@Repository("BookDaoHibernateImplementation")
@Qualifier("BookDaoHibernate")
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManager sessionFactory;

    @Override
    public Book addBook(Book book) {
        Session session = sessionFactory.unwrap(Session.class);
        book.setDateAdded(new Date());
        session.save(book);
        return book;
    }

    @Override
    public Set<Book> queryBooks() {
        return null;
    }

    @Override
    public Set<Author> queryBookAuthor(int bookId) {
        Transaction transaction = null;
        Set<Author> authors = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            /** LOGIC **/
            transaction.commit();
        } catch (Exception exc) {
            exc.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return authors;
    }

    @Override
    public Set<Tag> queryBookTags(int bookId) {
        return null;
    }

    @Override
    public Book queryBook(int id) {
        return null;
    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        return null;
    }
}
