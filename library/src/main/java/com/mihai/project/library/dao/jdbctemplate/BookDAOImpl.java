package com.mihai.project.library.dao.jdbctemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("BookDaoJDBCTemplateImplementation")
@Qualifier("BookDaoJDBCTemplate")
public class BookDAOImpl  {

  /*  private JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @BookAOP
    public Book addBook(Book book) {
        Map<String, Object> values = new HashMap<>();
        //@@ Insert book and get id after insert.
        Number bookId = addBookDB(book, values);
        resolveDBConsistencyAfterInsertOrUpdateBook(book, values, bookId.intValue());
        return queryBook(bookId.intValue());
    }

    @Override
    public Set<Book> queryBooks() {
        List<Book> books = jdbcTemplate.query(MyQuery.QUERY_BOOKS,
                (res, num) -> {
                    Book book = new Book(res.getInt(1), res.getString(2), res.getString(3), res.getTimestamp(4), null, null);
                    book.setTags(queryBookTags(book.getId()));
                    book.setAuthors(queryBookAuthor(book.getId()));
                    return book;
                });
        return new HashSet<>(books);
    }

    @Override
    public Set<Author> queryBookAuthor(int bookId) {
        Set<Author> bookAuthors = null;
        try {
            List<Author> bookAuthorsList = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_AUTHOR_BY_ID, new Object[]{bookId},
                    (res, num) -> new Author(res.getInt(1), res.getString(2)));
            bookAuthors = new HashSet<>(bookAuthorsList);
        } catch (EmptyResultDataAccessException exc) {
        }
        return bookAuthors;
    }

    @Override
    public Set<BookTag> queryBookTags(int bookId) {
        Set<BookTag> bookTags = null;
        try {
            List<BookTag> bookTagsList = jdbcTemplate.query(MyQuery.QUERY_ALL_BOOK_TAGS, new Object[]{bookId}, (res, num) -> new BookTag(res.getInt(1), res.getString(2)));
            bookTags = new HashSet<>(bookTagsList);
        } catch (EmptyResultDataAccessException exc) {
        }
        return bookTags;
    }

    @Override
    public Book queryBook(int id) {
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_BOOK, new Object[]{id},
                    (res, num) -> new Book(res.getInt(1), res.getString(2), res.getString(3), res.getTimestamp(4), null, null));
            book.setAuthors(queryBookAuthor(book.getId()));
            book.setTags(queryBookTags(book.getId()));
        } catch (EmptyResultDataAccessException exc) {
        }
        return book;
    }

    @Override
    public boolean deleteBook(int id) {
        if (jdbcTemplate.update(MyQuery.DELETE_BOOK, new Object[]{id}) == 1)
            return true;
        return false;
    }

    @Override
    public Book updateBook(Book book, int bookId) {
        Book existingBook = queryBook(bookId);
        if (existingBook == null)
            return null;
        else
            return updateBookUsingData(book, bookId);
    }

    private Number addBookDB(Book book, Map<String, Object> values) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK).usingGeneratedKeyColumns(MyTable.BOOK_ID);
        putBookValues(book, values);
        Number bookId = insert.executeAndReturnKey(values);
        values.clear();
        return bookId;
    }

    private List<Integer> addBookAuthors(Set<Author> authors, Map<String, Object> values) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.AUTHOR).usingGeneratedKeyColumns(MyTable.AUTHOR_ID);
        List<Integer> authorId = new ArrayList<>();
        for (Author auth : authors) {
            try {
                //@@ Check if author exists and prevent duplicate
                Author authorExist = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_AUTHOR, new Object[]{auth.getName()}, (res, num) -> new Author(res.getInt(1), res.getString(2)));
                authorId.add(authorExist.getId());
            } catch (EmptyResultDataAccessException exc) {
                values.put(MyTable.AUTHOR_NAME, auth.getName());
                authorId.add((insert.executeAndReturnKey(values).intValue()));
                values.clear();
            }
        }
        return authorId;
    }

    private void resolveManyToManyBookAuthor(List<Integer> authorId, Map<String, Object> values, Number bookId) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_AUTHOR);
        for (Integer authid : authorId) {
            try {
                Vector<Integer> pairExist = jdbcTemplate.queryForObject(MyQuery.QUERY_BOOK_AUTHOR_PAIR, new Object[]{bookId, authid}, (res, num) -> new Vector<>());

            } catch (EmptyResultDataAccessException exc) {
                values.put(MyTable.BOOK_AUTHOR_BOOK_ID, bookId);
                values.put(MyTable.BOOK_AUTHOR_AUTHOR_ID, authid);
                insert.execute(values);
                values.clear();
            }
        }
    }

    private List<Integer> addBookTags(Set<BookTag> bookTags, Map<String, Object> values) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.TAG_TABLE).usingGeneratedKeyColumns(MyTable.TAG_ID);
        List<Integer> tagsId = new ArrayList<>();
        for (BookTag bookTag : bookTags) {
            try {
                BookTag bookTagResult = jdbcTemplate.queryForObject(MyQuery.QUERY_SINGLE_TAG, new Object[]{bookTag.getTag()}, (res, num) -> new BookTag(res.getInt(1), res.getString(2)));
                tagsId.add(bookTagResult.getId());
            } catch (EmptyResultDataAccessException exc) {
                values.put(MyTable.TAG_FIELD, bookTag.getTag());
                tagsId.add(insert.executeAndReturnKey(values).intValue());
                values.clear();
            }
        }
        return tagsId;
    }

    private void resolveManyToManyBookTag(List<Integer> tagsId, Map<String, Object> values, Number bookId) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(MyTable.BOOK_TAG);
        for (Integer tag : tagsId) {
            try {
                Vector<Integer> pairExist = jdbcTemplate.queryForObject(MyQuery.QUERY_BOOK_TAG_PAIR, new Object[]{bookId, tag}, (res, num) -> new Vector<>());
            } catch (EmptyResultDataAccessException exc) {
                values.put(MyTable.BOOK_TAG_BOOK_ID, bookId);
                values.put(MyTable.BOOK_TAG_TAG_ID, tag);
                insert.execute(values);
                values.clear();
            }
        }
    }

    private Book updateBookUsingData(Book book, int id) {
        Map<String, Object> values = new HashMap<>();
        jdbcTemplate.update(MyQuery.UPDATE_BOOK, book.getTitle(), book.getDescription(), id);
        resolveDBConsistencyAfterInsertOrUpdateBook(book, values, id);
        return queryBook(id);
    }

    private void resolveDBConsistencyAfterInsertOrUpdateBook(Book book, Map<String, Object> values, int bookId) {
        List<Integer> idForResolveManyToMany;
        //@@ Add book authors
        idForResolveManyToMany = addBookAuthors(book.getAuthors(), values);
        //@@ Resolve many to many relation between book and author
        resolveManyToManyBookAuthor(idForResolveManyToMany, values, bookId);
        //@@ Insert book tags
        idForResolveManyToMany.clear();
        idForResolveManyToMany = addBookTags(book.getTags(), values);
        //@@ Resolve many to many relation between book and tag
        resolveManyToManyBookTag(idForResolveManyToMany, values, bookId);
    }

    private Map<String, Object> putBookValues(Book book, Map<String, Object> values) {
        values.put(MyTable.BOOK_TITLE, book.getTitle());
        values.put(MyTable.BOOK_DATE_ADDED, new Date());
        values.put(MyTable.BOOK_TABLE_DESCRIPTION, book.getDescription());
        return values;
    }*/

}
