package com.mihai.project.library.util;

public class MyTable {
    //@@ Book
    public static String BOOK= "books";
    public static String BOOK_ID= "id";
    public static String BOOK_TITLE= "title";
    public static String BOOK_DATE_ADDED= "dateAdded";

    //@@ Author
    public static String AUTHOR = "authors";
    public static String AUTHOR_ID = "id";
    public static String AUTHOR_NAME = "name";

    //@@ Book-Author
    public static String BOOK_AUTHOR = "booksAuthors";
    public static String BOOK_AUTHOR_BOOK_ID = "bookId";
    public static String BOOK_AUTHOR_AUTHOR_ID = "authorId";

    //@@ Book Desc
    public static String BOOK_TABLE_DESCRIPTION= "description";

    //@@ Book Tag
    public static String TAG_TABLE = "bookTags";
    public static String TAG_ID = "id";
    public static String TAG_FIELD = "tag";

    //@@ Book Tag Many To Many
    public static String BOOK_TAG = "bookTagManyToMany";
    public static String BOOK_TAG_BOOK_ID = "bookId";
    public static String BOOK_TAG_TAG_ID = "tagId";

    //@@ User
    public static String USER = "appusers";
    public static String USER_ID = "username";
    public static String USER_PASSWORD = "password";
    public static String USER_EMAIL = "email";
    public static String USER_ENABLE = "enabled";
    public static String USER_ROLE = "role";
    public static String HIBERNATE_USER_NEW = "newusername";
}
