package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.service.stock.CopyStockService;
import com.mihai.project.library.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    private AuthorService authorService;

    private TagService bookTagService;

    private CopyStockService copyStockService;

    public BookServiceImpl(@Qualifier("BookDaoHibernate") BookDAO bookDAO, AuthorService authorService, TagService bookTagService, CopyStockService copyStockService) {
        this.bookDAO = bookDAO;
        this.authorService = authorService;
        this.bookTagService = bookTagService;
        this.copyStockService = copyStockService;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return addBook(book, 1);
    }

    @Override
    @Transactional
    public Book addBook(Book book, int copyNumber) {
        clearAndUpdate(book);
        Book bookInserted = bookDAO.addBook(book);
        copyStockService.addBookCopy(book, copyNumber);
        return bookInserted;
    }

    @Override
    @Transactional
    public Set<Book> queryBooks() {
        return bookDAO.queryBooks();
    }

    @Override
    @Transactional
    public Set<Author> queryBookAuthor(int bookId) {
        return bookDAO.queryBookAuthor(bookId);
    }

    @Override
    @Transactional
    public Set<Tag> queryBookTags(int bookId) {
        return bookDAO.queryBookTags(bookId);
    }

    @Override
    @Transactional
    public Book queryBook(int id) {
        Book t = bookDAO.queryBook(id);
        System.out.println(t);
        return bookDAO.queryBook(id);
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        return bookDAO.deleteBook(id);
    }

    @Override
    @Transactional
    public Book updateBook(Book book, int bookId) {
        clearAndUpdate(book);
        return bookDAO.updateBook(book, bookId);
    }

    /**
     * In case of a book who already has an id and all authors and tags has an id
     * It's more like a real world update. !! See BookDAOImpl - Hibernate
     **/
    @Override
    @Transactional
    public Book updateBookUsingTagAndAuthorId(Book book) {
        return bookDAO.updateBookUsingTagAndAuthorId(book);

    }

    private void clearAndUpdate(Book book) {
        Map<String, Set<Author>> mapOfAuthors = resolveExistingAuthorOrTag(book.getAuthors(), Author.class);
        Map<String, Set<Tag>> mapOfTags = resolveExistingAuthorOrTag(book.getTags(), Tag.class);
        book.getTags().clear();
        book.getTags().addAll(mergeSet(mapOfTags.get("existing"), mapOfTags.get("new")));
        book.getAuthors().clear();
        book.getAuthors().addAll(mergeSet(mapOfAuthors.get("existing"), mapOfAuthors.get("new")));
    }

    private <T, C> Map<String, Set<T>> resolveExistingAuthorOrTag(Set<T> authorsToResolve, C cast) {
        Map<String, Set<T>> resolveResult = new HashMap<>();
        Set<T> existingValues = new HashSet<>();
        Set<T> newValues = new HashSet<>();
        return resolve(resolveResult, authorsToResolve, existingValues, newValues, cast);
    }

    private <T, C> Map<String, Set<T>> resolve(Map<String, Set<T>> resolveResult, Set<T> listToResolve, Set<T> existingValues, Set<T> newValues, C cast) {
        listToResolve.stream().forEach(item -> {
            Method method;
            System.out.println(cast);
            try {
                method = item.getClass().getMethod("getName");
                T existingItem = null;
                if (cast.equals(Author.class)) {
                    existingItem = authorService.querySingleAuthorForBookValidation((String) method.invoke(item));
                } else if (cast.equals(Tag.class)) {
                    existingItem = bookTagService.querySingleTagForBookValidation((String) method.invoke(item)); // Generic Type
                }
                if (existingItem == null) {
                    newValues.add(item);
                } else {
                    existingValues.add(existingItem);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        resolveResult.put("existing", existingValues);
        resolveResult.put("new", newValues);
        return resolveResult;
    }

    private <T> Set<T> mergeSet(Set<T> set1, Set<T> set2) {
        set1.addAll(set2);
        return set1;
    }

}
