package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookDesc;
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
    public Number addBook(Book book) {
        Map<String, Object> values = new HashMap<>();
        //@@ Insert book and get id after insert.
        Number bookId = addBookDB(book, values);
        //@@ Insert all authors and store id in authorId
        List<Integer> authorId = addBookAuthors(book.getAuthors(), values);
        //@@ Resolve many to many relations between book and author
        resolveManyToManyBookAuthor(authorId, values, bookId);
        //@@ Insert all book's descriptions
        addBookDescriptions(book.getBookDescriptions(), values, bookId);
        //@@ Insert book tags
        addBookTags(book.getTags(), values, bookId);
        return bookId;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books =  jdbcTemplate.query(MyQuery.QUERY_BOOKS,
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3), null, null, null);});
        return books;
    }

    @Override
    public List<Author> queryBookAuthor(int bookId) {
        List<Author> bookAuthors = null;
        try{
            bookAuthors = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_AUTHOR_BY_ID, new Object[]{bookId},
                    (res, num)->{return  new Author(res.getInt(1), res.getString(2));});
        }catch(EmptyResultDataAccessException exc){ }
        return bookAuthors;
    }

    @Override
    public List<BookDesc> queryBookDescriptions(int bookId) {
        List<BookDesc> bookAuthors = null;
        try{
            bookAuthors = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_DESCRIPTIONS, new Object[]{bookId},
                    (res, num)->{return  new BookDesc(res.getInt(1), res.getString(2), res.getInt(3));});
        }catch(EmptyResultDataAccessException exc){ }
        return bookAuthors;
    }

    @Override
    public List<BookTag> queryBookTags(int bookId) {
        return null;
    }


    @Override
    public Book queryBook(int id) {
        Book book = null;
        try{
            book =  jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_BOOK, new Object[]{id},
                    (res, num)->{ return new Book(res.getInt(1), res.getString(2), res.getDate(3));});
            book.setAuthors(queryBookAuthor(book.getId()));
            book.setBookDescriptions(queryBookDescriptions(book.getId()));
        }catch(EmptyResultDataAccessException exc){ }

        return book;
    }

    private Number addBookDB(Book book, Map<String, Object> values){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK).usingGeneratedKeyColumns(MyTable.BOOK_ID);
        values.put(MyTable.BOOK_TITLE, book.getTitle());
        values.put(MyTable.BOOK_DATE_ADDED, book.getDateAdded());
        Number bookId = insert.executeAndReturnKey(values);
        values.clear();
        return  bookId;
    }

    private List<Integer> addBookAuthors(List<Author> authors, Map<String, Object> values){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
        List<Integer> authorId = new ArrayList<>();
        for(Author auth: authors){
            //@@ Check if author exists
            try{
                Author authorExist = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_AUTHOR, new Object[]{auth.getName()}, (res, num)->{ return new Author(res.getInt(1), res.getString(2));});
                authorId.add(authorExist.getId());
            }catch(EmptyResultDataAccessException exc){
                values.put(MyTable.AUTHOR_NAME, auth.getName());
                authorId.add((insert.executeAndReturnKey(values).intValue()));
                values.clear();
            }
        }
        return authorId;
    }

    private void resolveManyToManyBookAuthor(List<Integer> authorId, Map<String, Object> values, Number bookId){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
        for(Integer authid: authorId){
            try{
                Vector<Integer> pairExist = jdbcTemplate.queryForObject(MyQuery.QUERY_BOOK_AUTHOR_PAIR, new Object[]{bookId, authorId}, (res, num)->{return new Vector<>();});
            }catch(EmptyResultDataAccessException exc){
                values.put(MyTable.BOOK_AUTHOR_BOOK_ID, bookId);
                values.put(MyTable.BOOK_AUTHOR_AUTHOR_ID, authid);
                insert.execute(values);
                values.clear();
            }
        }
    }

    private void addBookDescriptions(List<BookDesc> bookDescParam , Map<String, Object> values, Number bookId){
       SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_DESCRIPTION);
        for(BookDesc bookDesc: bookDescParam){
            values.put(MyTable.BOOK_DESCRIPTION_DESC, bookDesc.getDescription());
            values.put(MyTable.BOOK_DESCRIPTION_STATUS, bookDesc.getStatus());
            values.put(MyTable.BOOK_DESCRIPTION_BOOK_ID, bookId);
            insert.execute(values);
            values.clear();
        }
    }

    private void addBookTags(List<BookTag> bookTags, Map<String, Object> values, Number bookId){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.TAG_TABLE);
        for(BookTag bookTag: bookTags){
            values.put(MyTable.TAG_FIELD, bookTag.getTag());
            values.put(MyTable.TAG_BOOK_ID, bookId);
            insert.execute(values);
            values.clear();
        }
    }

}
