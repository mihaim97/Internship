package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import com.mihai.project.library.util.enumeration.AuthorType;
import com.mihai.project.library.util.enumeration.TagType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

@Repository("BookDaoHibernateImplementation")
@Qualifier("BookDaoHibernate")
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book addBook(Book book) {
        //TODO Implement logic for duplicate entry key..
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = null;
        try(Session hsession = sessionFactory.openSession()){
            transaction = hsession.getTransaction();
            transaction.begin();
            Map<AuthorType, Set<Author>> authors = checkForExistingAuthor(book.getAuthors(), hsession);
            //Map<TagType, Set<Author>> tags = checkForExistingAuthor(book.getAuthors(), hsession);

            transaction.commit();
        }catch(Exception exc){
            if (transaction != null){
                transaction.rollback();
            }
        }


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

    private <T> T getSingleResultOrNullFromQueryGeneric(Query<T> query){
        try{
            T user = query.getSingleResult();
            return user;
        }catch(NoResultException exc){
            return null;
        }
    }

    private Map<AuthorType, Set<Author>> checkForExistingAuthor(Set<Author> authors, Session session){
        Transaction transaction = null;
        Map<AuthorType, Set<Author>> resolveAuthor = new HashMap<>();
        Set<Author> existingAuthor = new HashSet<>();
        Set<Author> newAuthor = new HashSet<>();
        Query<Author> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_AUTHOR_BY_NAME);
        authors.stream().forEach(auth ->{
            query.setParameter(MyTable.AUTHOR_NAME, auth.getName());
            Author author = getSingleResultOrNullFromQueryGeneric(query);
            if(auth == null){
                newAuthor.add(auth);
            }else{
                existingAuthor.add(author);
            }
        });
        resolveAuthor.put(AuthorType.EXISTING, existingAuthor);
        resolveAuthor.put(AuthorType.NEW, newAuthor);
        transaction.commit();
        return resolveAuthor;
    }

  /*  private <Map<K,Set<T>>> Map<K, Set<Author>> checkForExistingAuthorOrTag(Set<Author> authors, Session session){
        Transaction transaction = null;
        Map<AuthorType, Set<Author>> resolveAuthor = new HashMap<>();
        Set<Author> existingAuthor = new HashSet<>();
        Set<Author> newAuthor = new HashSet<>();
        Query<Author> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_AUTHOR_BY_NAME);
        authors.stream().forEach(auth ->{
            query.setParameter(MyTable.AUTHOR_NAME, auth.getName());
            Author author = getSingleResultOrNullFromQueryGeneric(query);
            if(auth == null){
                newAuthor.add(auth);
            }else{
                existingAuthor.add(author);
            }
        });
        resolveAuthor.put(AuthorType.EXISTING, existingAuthor);
        resolveAuthor.put(AuthorType.NEW, newAuthor);
        transaction.commit();
        return resolveAuthor;
    }*/


}
