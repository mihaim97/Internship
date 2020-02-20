package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.BookDesc;
import com.mihai.project.library.entity.book.BookTag;
import com.mihai.project.library.service.AuthorService;
import com.mihai.project.library.service.BookDescriptionService;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        //@@ Insert book and get id after insert.
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK).usingGeneratedKeyColumns(MyTable.BOOK_ID);
        Map<String, Object> values = new HashMap<>();
        values.put(MyTable.BOOK_TITLE, book.getTitle());
        values.put(MyTable.BOOK_DATE_ADDED, book.getDateAdded());
        Number bookId = insert.executeAndReturnKey(values);
        values.clear();
        //@@ Insert all authors and store id in authorId
        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
        List<Integer> authorId = new ArrayList<>();
        for(Author auth: book.getAuthors()){
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
        System.out.println(authorId);
        //@@ Resolve many to many relations between book and author
        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
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
        //@@ Insert all book's descriptions
        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_DESCRIPTION);
        for(BookDesc bookDesc: book.getBookDescriptions()){
            values.put(MyTable.BOOK_DESCRIPTION_DESC, bookDesc.getDescription());
            values.put(MyTable.BOOK_DESCRIPTION_STATUS, bookDesc.getStatus());
            values.put(MyTable.BOOK_DESCRIPTION_BOOK_ID, bookId);
            insert.execute(values);
            values.clear();
        }
        //@@ Insert book tags
        insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.TAG_TABLE);
        for(BookTag bookTag: book.getTags()){
            values.put(MyTable.TAG_FIELD, bookTag.getTag());
            values.put(MyTable.TAG_BOOK_ID, bookId);
            insert.execute(values);
            values.clear();
        }
        return bookId;
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books =  jdbcTemplate.query(MyQuery.QUERY_BOOKS,
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3), null, null, null);});
        return books;
    }

    @Override
    public Book queryBook(int id) {
        Book books =  jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_BOOK, new Object[]{id},
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3), null, null, null);});
        return books;
    }

    private void addBookAuthorLink(Number bookId, Number authorId){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_AUTHOR);
        Map<String, Object> values = new HashMap<>();
        values.put(MyTable.BOOK_AUTHOR_BOOK_ID, bookId.intValue());
        values.put(MyTable.BOOK_AUTHOR_AUTHOR_ID, authorId.intValue());
        insert.execute(values);
    }
}
