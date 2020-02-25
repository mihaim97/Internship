package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.entity.user.User;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.Set;

@Repository("BookDaoHibernateImplementation")
@Qualifier("BookDaoHibernate")
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Book addBook(Book book) {
        //TODO Implement logic for duplicate entry key..
        Session session = entityManager.unwrap(Session.class);
        book.setDateAdded(new Date());
        System.out.println(book.getAuthors());
        session.saveOrUpdate(book);
        return new Book();
    }

    @Override
    public Set<Book> queryBooks() {
        return null;
    }

    @Override
    public Set<Author> queryBookAuthor(int bookId) {
        return null;
    }

    @Override
    public Set<BookTag> queryBookTags(int bookId) {
        return null;
    }

    @Override
    public Book queryBook(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Book> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_BOOK);
        query.setParameter(MyTable.BOOK_ID, id);
        return getSingleResultOrNullFromQuery(query);
    }

    @Override
    public boolean deleteBook(int id) {
        return false;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        return null;
    }

    private Book getSingleResultOrNullFromQuery(Query<Book> query){
        try{
            Book user = query.getSingleResult();
            return user;
        }catch(NoResultException exc){
            return null;
        }
    }
}
