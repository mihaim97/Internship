package com.mihai.project.library.dao.jdbctemplate;

import com.mihai.project.library.dao.BookDescriptionDAO;
import com.mihai.project.library.entity.book.BookDesc;
import com.mihai.project.library.util.MyTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookDescriptionDAOImpl implements BookDescriptionDAO {

    private JdbcTemplate jdbcTemplate;

    public BookDescriptionDAOImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void addBookDescription(int bookId, BookDesc bookDesc) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_DESCRIPTION).usingGeneratedKeyColumns(MyTable.BOOK_DESCRIPTION_ID);
        Map<String, Object> values = new HashMap<>();
        values.put(MyTable.BOOK_DESCRIPTION_DESC, bookDesc.getDescription());
        values.put(MyTable.BOOK_DESCRIPTION_STATUS, bookDesc.getStatus());
        values.put(MyTable.BOOK_DESCRIPTION_BOOK_ID, bookId);
        insert.executeAndReturnKey(values);
    }
}
