package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.BookDAO;
import com.mihai.project.library.entity.book.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("books").usingGeneratedKeyColumns("id");
        Map<String, Object> values = new HashMap<>();
        values.put("title", book.getTitle());
        values.put("dateAdded", book.getDateAdded());
        insert.execute(values);
    }

    @Override
    public List<Book> queryBooks() {
        String query = "select * from books";
        List<Book> books =  jdbcTemplate.query(query,
                (res, num)->{ return new Book( res.getInt(1), res.getString(2), res.getDate(3));});
        return books;
    }

    @Override
    public Book queryBook(int id) {
        return null;
    }
}
