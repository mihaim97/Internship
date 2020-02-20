package com.mihai.project.library.util;

public class MyQuery {
    // Book
    public static String QUERY_BOOKS= "select * from books";
    public static String QUERY_SINGLE_BOOK= "select * from books where id = ?";
    public static String ADD_BOOK = "";
    public static String QUERY_ALL_BOOK_AUTHOR_BY_ID = "select auth.* \n" +
            "from authors auth inner join booksauthors ab on auth.id = ab.authorId inner join books b on ab.bookId = b.id\n" +
            "where b.id = ?";
    public static String QUERY_ALL_BOOK_DESCRIPTIONS = "select bd.* from books b inner join bookdescriptions bd on b.id = bd.bookId where b.id = ?;";
    public static String QUERY_ALL_BOOK_TAGS = "";

    // Author
    public static String QUERY_SINGLE_AUTHOR= "select * from authors where name = ?";

    // Book - Author M-M
    public static String ADD_MANY_TO_MANY_LINK = "insert into booksAuthors values(?,?)";

    // Book-Author
    public static String QUERY_BOOK_AUTHOR_PAIR = "select * from booksauthors where bookId = ? and authorId = ?";
}
