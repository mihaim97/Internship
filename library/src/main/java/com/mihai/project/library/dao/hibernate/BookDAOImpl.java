package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("BookDaoHibernateImplementation")
@Qualifier("BookDaoHibernate")
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        book.setDateAdded(new Date());
        session.persist(book);
        return book;
    }

    @Override
    public Set<Book> queryBooks() {
        Session session = sessionFactory.getCurrentSession();
        return new HashSet<>(session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOKS).getResultList());
    }

    @Override
    public Set<Author> queryBookAuthor(int bookId) {
        return null;
    }

    @Override
    public Set<Tag> queryBookTags(int bookId) {
        return null;
    }

    @Override
    public Book queryBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Book.class, id);
    }

    @Override
    public boolean deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MyQuery.HIBERNATE_DELETE_BOOK_BY_ID);
        query.setParameter(MyTable.BOOK_ID, id);
       /* Book book = session.find(Book.class, id);
        book.setAuthors(null);
        book.setTags(null);
        session.delete(book);*/
        return query.executeUpdate() == 1;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book existingBook = queryBook(bookId);
        existingBook.getAuthors().addAll(book.getAuthors());
        existingBook.getTags().addAll(book.getTags());
        existingBook.setDescription(book.getDescription());
        existingBook.setTitle(book.getTitle());
        return existingBook;
    }

    /**
     * This also remove an author or tag from a book
     * The first one add all authors and tags
     * This is equivalent to book.setTags()
     * It is necessary for book to have an id
     **/
    @Override
    public Book updateBookUsingTagAndAuthorId(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(book);
        return book;
    }


}
