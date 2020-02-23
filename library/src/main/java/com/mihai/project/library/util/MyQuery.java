package com.mihai.project.library.util;

public class MyQuery {
    //@@ Book
    public static String QUERY_BOOKS= "select * from books";
    public static String QUERY_SINGLE_BOOK= "select * from books where id = ?";
    public static String ADD_BOOK = "";
    public static String QUERY_ALL_BOOK_AUTHOR_BY_ID = "select auth.* \n" +
            "from authors auth inner join booksauthors ab on auth.id = ab.authorId inner join books b on ab.bookId = b.id\n" +
            "where b.id = ?";
    public static String QUERY_ALL_BOOK_TAGS = "select bt.* from books b inner join bookTagManyToMany btm on b.id = btm.bookId inner join booktags bt on btm.tagId = bt.id\n" +
            "where b.id = ?";
    public static String DELETE_BOOK = "delete from books where id = ?";
    public static String UPDATE_BOOK = "update books set title = ?, description = ? where id = ?";

    //@@ Author
    public static String QUERY_SINGLE_AUTHOR= "select * from authors where name = ?";

    //@@ Book-Author
    public static String QUERY_BOOK_AUTHOR_PAIR = "select * from booksAuthors where bookId = ? and authorId = ?";

    //@@
    public static String QUERY_BOOK_TAG_PAIR = "select * from bookTagManyToMany where bookId = ? and tagId = ?";

    //@@ Tag
    public static String QUERY_SINGLE_TAG = "select * from bookTags where tag = ?";

    //@@ User
    public static String QUERY_SINGLE_USER = "select * from appusers where username = ?";
    public static String QUERY_SINGLE_USER_BY_EMAIL = "select * from appusers where email = ?";
    public static String QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER = "select * from appusers where email = ? and username <> ?";
    public static String QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER = "select * from appusers where username = ? and username <> ?";
    public static String DELETE_USER = "delete from appusers where username = ?";
    public static String QUERY_ALL_USERS = "select * from appusers";
    public static String UPDATE_USER= "update appusers set username = ?, password = ?, email = ?, role = ? where username = ?";

}
