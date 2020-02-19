package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.service.AuthorService;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    private AuthorService authorService;

    public BookDAOImpl(JdbcTemplate jdbcTemplate, AuthorService authorService){
        this.jdbcTemplate = jdbcTemplate;
        this.authorService = authorService;
    }

    @Override
    public Number addBook(Book book) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK).usingGeneratedKeyColumns(MyTable.BOOK_ID);
        Map<String, Object> values = new HashMap<>();
        values.put(MyTable.BOOK_TITLE, book.getTitle());
        values.put(MyTable.BOOK_DATE_ADDED, book.getDateAdded());
        book.getAuthors().stream().forEach(author -> {
            Number authorId = authorService.addAuthor(author);
            // De facut legatura in tabelul booksAuthors pentru many to many
        });
        return insert.executeAndReturnKey(values);
    }

    @Override
    public List<Book> queryBooks() {
        List<Book> books =  jdbcTemplate.query(MyQuery.QUERY_BOOKS,
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3), null);});
        return books;
    }

    @Override
    public Book queryBook(int id) {
        Book books =  jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_BOOK, new Object[]{id},
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3), null);});
        return books;
    }
}
