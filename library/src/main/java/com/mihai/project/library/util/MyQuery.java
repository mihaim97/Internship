package com.mihai.project.library.util;

public class MyQuery {
    // Book
    public static String QUERY_BOOKS= "select * from books";
    public static String QUERY_SINGLE_BOOK= "select * from books where id = ?";
    public static String ADD_BOOK = "";

    // Author
    public static String QUERY_SINGLE_AUTHOR= "select * from authors where name = ?";

    // Book - Author M-M
    public static String ADD_MANY_TO_MANY_LINK = "insert into booksAuthors values(?,?)";

    // Book-Author
    public static String QUERY_BOOK_AUTHOR_PAIR = "select * from booksauthors where bookId = ? and authorId = ?";
}
