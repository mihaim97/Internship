package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private JdbcTemplate jdbcTemplate;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public Number addAuthor(Author author) {
        Author authorQuery = querySingleAuthor(author.getName());
        if(authorQuery == null){
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
            Map<String, Object> values = new HashMap<>();
            values.put(MyTable.AUTHOR_NAME, author.getName());
            return insert.executeAndReturnKey(values);
        }
        else
            return authorQuery.getId();
    }

    @Override
    public List<Author> queryAuthors() {
        return null;
    }

    @Override
    public Author querySingleAuthor(String name) {
        Author books = null;
        try{
            books =  jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_AUTHOR, new Object[]{name},
                    (res, num)->{ return new Author( res.getInt(1), res.getString(2));});
        }catch(EmptyResultDataAccessException exc){ }

        return books;
    }
}
