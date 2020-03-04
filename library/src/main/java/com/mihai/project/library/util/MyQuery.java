package com.mihai.project.library.util;

public class MyQuery {
    /**
     * JDBC
     **/
    //@@ Book
    public static String QUERY_BOOKS = "select * from books";
    public static String QUERY_SINGLE_BOOK = "select * from books where id = ?";
    public static String QUERY_ALL_BOOK_AUTHOR_BY_ID = "select auth.* \n" +
            "from authors auth inner join books_authors ab on auth.id = ab.authorid inner join books b on ab.bookid = b.id\n" +
            "where b.id = ?";
    public static String QUERY_ALL_BOOK_TAGS = "select bt.* from books b inner join book_tag_many_to_many btm on b.id = btm.bookid inner join book_tags bt on btm.tagid = bt.id\n" +
            "where b.id = ?";
    public static String DELETE_BOOK = "delete from books where id = ?";
    public static String UPDATE_BOOK = "update books set title = ?, description = ? where id = ?";

    //@@ Author
    public static String QUERY_SINGLE_AUTHOR = "select * from authors where name = ?";

    //@@ Book-Author
    public static String QUERY_BOOK_AUTHOR_PAIR = "select * from books_authors where bookId = ? and authorId = ?";

    //@@
    public static String QUERY_BOOK_TAG_PAIR = "select * from book_tag_many_to_many where bookid = ? and tagid = ?";

    //@@ Tag
    public static String QUERY_SINGLE_TAG = "select * from book_tags where tag = ?";

    //@@ User
    public static String QUERY_SINGLE_USER = "select * from appusers where username = ?";
    public static String QUERY_SINGLE_USER_BY_EMAIL = "select * from appusers where email = ?";
    public static String QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER = "select * from appusers where email = ? and username <> ?";
    public static String QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER = "select * from appusers where username = ? and username <> ?";
    public static String DELETE_USER = "delete from appusers where username = ?";
    public static String QUERY_ALL_USERS = "select * from appusers";
    public static String UPDATE_USER = "update appusers set username = ?, password = ?, email = ?, role = ? where username = ?";

    /**
     * HIBERNATE
     **/
    //@ User
    public static String HIBERNATE_QUERY_ALL_USERS = "from User";
    public static String HIBERNATE_QUERY_USER_BY_USERNAME = "from User where username = :username";
    public static String HIBERNATE_QUERY_USER_BY_EMAIL = "from User where email = :email";
    public static String HIBERNATE_QUERY_SINGLE_USER_BY_EMAIL_EXCEPT_CURRENT_USER = "from User where email = :email and username <> :username";
    public static String HIBERNATE_QUERY_SINGLE_USER_BY_USERNAME_EXCEPT_CURRENT_USER = "from User where username = :newusername and username <> :username";
    public static String HIBERNATE_DELETE_USER = "delete User where username = :username";

    //@@ Book
    public static String HIBERNATE_QUERY_SINGLE_BOOK = "from Book where id = :id";
    public static String HIBERNATE_QUERY_ALL_BOOKS = "from Book";
    public static String HIBERNATE_DELETE_BOOK_BY_ID = "delete from Book where id = :id";

    //@@ Author
    public static String HIBERNATE_QUERY_SINGLE_AUTHOR_BY_NAME = "from Author where name = :name";

    //@@Tag
    public static String HIBERNATE_QUERY_SINGLE_TAG = "from Tag where tag = :tag";
    public static String HIBERNATE_QUERY_ALL_TAGS = "from Tag";
    public static String HIBERNATE_QUERY_TAGS_LIKE = "from Tag where tag like :characters";

    //@@CopyStock
    public static String HIBERNATE_QUERY_ALL_BOOK_COPY = "from CopyStock where book_id = :bookId";

}
