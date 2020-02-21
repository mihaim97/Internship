package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.annotation.BookAOP;
import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;
    //private AuthorService authorService;
    //private BookDescriptionService bookDescriptionService;

    public BookDAOImpl(JdbcTemplate jdbcTemplate /*AuthorService authorService, BookDescriptionService bookDescriptionService*/){
        this.jdbcTemplate = jdbcTemplate;
      //  this.authorService = authorService;
       // this.bookDescriptionService = bookDescriptionService;
    }

    @Override
    @BookAOP
    public Book addBook(Book book) {
        Map<String, Object> values = new HashMap<>();
        List<Integer> idForResolveManyToMany;
        //@@ Insert book and get id after insert.
        Number bookId = addBookDB(book, values);
        //@@ Insert all authors and store id in authorId
        idForResolveManyToMany = addBookAuthors(book.getAuthors(), values);
        //@@ Resolve many to many relation between book and author
        resolveManyToManyBookAuthor(idForResolveManyToMany, values, bookId, "INSERT");
        //@@ Insert book tags
        idForResolveManyToMany.clear();
        idForResolveManyToMany = addBookTags(book.getTags(), values);
        //@@ Resolve many to many relation between book and tag
        resolveManyToManyBookTag(idForResolveManyToMany, values, bookId);
        return queryBook(bookId.intValue());
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books =  jdbcTemplate.query(MyQuery.QUERY_BOOKS,
                (res, num)-> {
                    Book book = new Book( res.getInt(1), res.getString(2), res.getString(3), res.getTimestamp(4), null, null);
                    book.setTags(queryBookTags(book.getId()));
                    book.setAuthors(queryBookAuthor(book.getId()));
                    return book;
                });
        return books;
    }

    @Override
    public List<Author> queryBookAuthor(int bookId) {
        List<Author> bookAuthors = null;
        try{
            bookAuthors = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_AUTHOR_BY_ID, new Object[]{bookId},
                    (res, num)-> new Author(res.getInt(1), res.getString(2)));
        }catch(EmptyResultDataAccessException exc){ }
        return bookAuthors;
    }

    @Override
    public List<BookTag> queryBookTags(int bookId) {
        List<BookTag> bookTags = null;
        try{
            bookTags = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_TAGS, new Object[]{bookId}, (res, num)->new BookTag(res.getInt(1), res.getString(2)));
        }catch(EmptyResultDataAccessException exc){ }
        return bookTags;
    }

    @Override
    public Book queryBook(int id) {
        Book book = null;
        try{
            book =  jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_BOOK, new Object[]{id},
                    (res, num)-> new Book(res.getInt(1), res.getString(2), res.getString(3), res.getTimestamp(4), null, null));
            book.setAuthors(queryBookAuthor(book.getId()));
            book.setTags(queryBookTags(book.getId()));
        }catch(EmptyResultDataAccessException exc){ }
        return book;
    }

    @Override
    public boolean deleteBook(int id) {
        if(jdbcTemplate.update(MyQuery.DELETE_BOOK, new Object[]{id}) == 1)
            return true;
        return false;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        Book existingBook = queryBook(bookId);
        if(existingBook == null)
            return null;
        else
            return updateBookUsingData(book, bookId);
    }

    private Number addBookDB(Book book, Map<String, Object> values){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK).usingGeneratedKeyColumns(MyTable.BOOK_ID);
        putBookValues(book, values);
        Number bookId = insert.executeAndReturnKey(values);
        values.clear();
        return  bookId;
    }

    private List<Integer> addBookAuthors(List<Author> authors, Map<String, Object> values){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
        List<Integer> authorId = new ArrayList<>();
        for(Author auth: authors){
            try{
                //@@ Check if author exists and prevent duplicate
                Author authorExist = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_AUTHOR, new Object[]{auth.getName()}, (res, num)-> new Author(res.getInt(1), res.getString(2)));
                authorId.add(authorExist.getId());
            }catch(EmptyResultDataAccessException exc){
                values.put(MyTable.AUTHOR_NAME, auth.getName());
                authorId.add((insert.executeAndReturnKey(values).intValue()));
                values.clear();
            }
        }
        return authorId;
    }

    private void resolveManyToManyBookAuthor(List<Integer> authorId, Map<String, Object> values, Number bookId, String type){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_AUTHOR);
        for(Integer authid: authorId){
            try{
                Vector<Integer> pairExist = jdbcTemplate.queryForObject(MyQuery.QUERY_BOOK_AUTHOR_PAIR, new Object[]{bookId, authid}, (res, num)-> new Vector<>());

            }catch(EmptyResultDataAccessException exc){
                values.put(MyTable.BOOK_AUTHOR_BOOK_ID, bookId);
                values.put(MyTable.BOOK_AUTHOR_AUTHOR_ID, authid);
                insert.execute(values);
                values.clear();
            }
        }
    }

    private List<Integer> addBookTags(List<BookTag> bookTags, Map<String, Object> values){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.TAG_TABLE).usingGeneratedKeyColumns(MyTable.TAG_ID);
        List<Integer> tagsId = new ArrayList<>();
        for(BookTag bookTag: bookTags){
            try{
                BookTag bookTagResult = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_TAG, new Object[]{bookTag.getTag()}, (res, num)-> new BookTag(res.getInt(1), res.getString(2)));
                tagsId.add(bookTagResult.getId());
            }catch(EmptyResultDataAccessException exc){
                exc.printStackTrace();
                values.put(MyTable.TAG_FIELD, bookTag.getTag());
                tagsId.add(insert.executeAndReturnKey(values).intValue());
                values.clear();
            }
        }
        return  tagsId;
    }

    private void resolveManyToManyBookTag(List<Integer> tagsId, Map<String, Object> values, Number bookId){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_TAG);
        for(Integer tag: tagsId){
            try{
                Vector<Integer> pairExist = jdbcTemplate.queryForObject(MyQuery.QUERY_BOOK_TAG_PAIR, new Object[]{bookId, tag}, (res, num)-> new Vector<>());
            }catch(EmptyResultDataAccessException exc){
                values.put(MyTable.BOOK_TAG_BOOK_ID, bookId);
                values.put(MyTable.BOOK_TAG_TAG_ID, tag);
                insert.execute(values);
                values.clear();
            }
        }
    }

    private Book updateBookUsingData(Book book, int id){
        Map<String, Object> values = new HashMap<>();
        List<Integer>  idForResolveManyToMany;
        jdbcTemplate.update(MyQuery.UPDATE_BOOK, book.getTitle(), book.getDescription(), id);
        idForResolveManyToMany = addBookAuthors(book.getAuthors(), values);
        System.out.println(idForResolveManyToMany);
        //@@ Resolve many to many relation between book and author
        resolveManyToManyBookAuthor(idForResolveManyToMany, values, id, "UPDATE");
        //@@ Insert book tags
        idForResolveManyToMany.clear();
        idForResolveManyToMany = addBookTags(book.getTags(), values);
        //@@ Resolve many to many relation between book and tag
        resolveManyToManyBookTag(idForResolveManyToMany, values, id);
        return book;
    }

    private Map<String, Object> putBookValues(Book book, Map<String, Object> values){
        values.put(MyTable.BOOK_TITLE, book.getTitle());
        values.put(MyTable.BOOK_DATE_ADDED, new Date());
        values.put(MyTable.BOOK_TABLE_DESCRIPTION, book.getDescription());
        return values;
    }

}
