package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
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
    private EntityManager entityManager;

    @Override
    public Book addBook(Book book) {
        book.setDateAdded(new Date());
        entityManager.persist(book);
        return book;
    }

    @Override
    public Set<Book> queryBooks() {
        return new HashSet<>(entityManager.createQuery(MyQuery.HIBERNATE_QUERY_ALL_BOOKS).getResultList());
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
        return entityManager.find(Book.class, id);
    }

    @Override
    public boolean deleteBook(int id) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_DELETE_BOOK_BY_ID);
        query.setParameter(MyTable.BOOK_ID, id);
        return query.executeUpdate() == 1;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        Book existingBook = queryBook(bookId);
        existingBook.getAuthors().addAll(book.getAuthors());
        existingBook.getTags().addAll(book.getTags());
        existingBook.setDescription(book.getDescription());
        existingBook.setTitle(book.getTitle());
        return existingBook;
    }

    /**
     * This also remove an author ot tag from a book
     * The first one add all authors and tags
     * This is equivalent to book.setTags()
     * It is necessary for book to have an id
     **/
    @Override
    public Book updateBookUsingTagAndAuthorId(Book book) {
        entityManager.merge(book);
        return book;
    }


}
