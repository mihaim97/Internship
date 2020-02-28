package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.enumeration.CastOperationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    private AuthorService authorService;

    private BookTagService bookTagService;

    public BookServiceImpl(@Qualifier("BookDaoHibernate") BookDAO bookDAO, AuthorService authorService, BookTagService bookTagService) {
        this.bookDAO = bookDAO;
        this.authorService = authorService;
        this.bookTagService = bookTagService;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        Map<String, Set<Author>> mapOfAuthors = resolveExistingAuthorOrTag(book.getAuthors(), CastOperationType.AUTHOR);
        Map<String, Set<Tag>> mapOfTags = resolveExistingAuthorOrTag(book.getTags(), CastOperationType.TAG);
      /*  mapOfTags.get("existing").stream().forEach(author -> {
            System.out.println(author.getTag() + " exc");
        });
        mapOfTags.get("new").stream().forEach(author -> {
            System.out.println(author.getTag() + " new");
        });
        Set<BookTag> allTags = mergeSet(mapOfTags.get("existing"), mapOfTags.get("new"));
        allTags.stream().forEach(tag -> {
            System.out.println(tag.getTag() + " merge " + tag.getId());
        });*/
       // book.getTags().clear();
       // book.getTags().addAll(mergeSet(mapOfTags.get("existing"), mapOfTags.get("new")));

        //return new Book();
        return bookDAO.addBook(book);
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
        return bookDAO.updateBook(book, bookId);
    }

    private <T> Map<String, Set<T>> resolveExistingAuthorOrTag(Set<T> authorsToResolve, CastOperationType cast) {
        Map<String, Set<T>> resolveResult = new HashMap<>();
        Set<T> existingValues = new HashSet<>();
        Set<T> newValues = new HashSet<>();
        if (cast == CastOperationType.AUTHOR) {
            return resolveExistingAuthor(resolveResult, authorsToResolve, existingValues, newValues);
        } else if (cast == CastOperationType.TAG) {
            return resolveExistingTag(resolveResult, authorsToResolve, existingValues, newValues);
        }
        return null;
    }

    private <T> Map<String, Set<T>> resolveExistingAuthor(Map<String, Set<T>> resolveResult, Set<T> listToResolve, Set<T> existingValues, Set<T> newValues) {
        listToResolve.stream().forEach(value -> {
            Author author = authorService.querySingleAuthorForBookValidation(((Author) value).getName());
            if (author == null) {
                newValues.add(value);
            } else {
                existingValues.add((T) author);
            }
        });
        resolveResult.put("existing", existingValues);
        resolveResult.put("new", newValues);
        return resolveResult;
    }

    private <T> Map<String, Set<T>> resolveExistingTag(Map<String, Set<T>> resolveResult, Set<T> listToResolve, Set<T> existingValues, Set<T> newValues) {
        listToResolve.stream().forEach(value -> {
            Tag tag = bookTagService.querySingleTahForBookValidation(((Tag) value).getTag());
            if (tag == null) {
                newValues.add(value);
            } else {
                existingValues.add((T) tag);
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
