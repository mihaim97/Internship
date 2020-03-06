package com.mihai.project.library.util;

public class MyTable {
    //@@ Book
    public static String BOOK = "books";
    public static String BOOK_ID = "id";
    public static String BOOK_TITLE = "title";
    public static String BOOK_DATE_ADDED = "date_added";

    //@@ Author
    public static String AUTHOR = "authors";
    public static String AUTHOR_ID = "id";
    public static String AUTHOR_NAME = "name";

    //@@ Book-Author
    public static String BOOK_AUTHOR = "books_authors";
    public static String BOOK_AUTHOR_BOOK_ID = "bookid";
    public static String BOOK_AUTHOR_AUTHOR_ID = "authorid";

    //@@ Book Desc
    public static String BOOK_TABLE_DESCRIPTION = "description";

    //@@ Book Tag
    public static String TAG_TABLE = "book_tags";
    public static String TAG_ID = "id";
    public static String TAG_FIELD = "tag";
    public static String TAG_CHAR = "characters";

    //@@ Book Tag Many To Many
    public static String BOOK_TAG = "book_tag_many_to_many";
    public static String BOOK_TAG_BOOK_ID = "bookid";
    public static String BOOK_TAG_TAG_ID = "tagid";

    //@@ User
    public static String USER = "appusers";
    public static String USER_ID = "username";
    public static String USER_PASSWORD = "password";
    public static String USER_EMAIL = "email";
    public static String USER_ENABLE = "enabled";
    public static String USER_ROLE = "role";
    public static String HIBERNATE_USER_NEW = "newusername";

    //@@CopyStock
    public static String COPY_STOCK_ID = "code";
    public static String COPY_BOOK_BOOK_ID = "bookId";
    public static String COPY_BOOK_BOOK_FK = "book_id";
    public static String COPY_BOOK_FLAG = "flag";
    public static String COPY_BOOK_STATUS = "status";

}
