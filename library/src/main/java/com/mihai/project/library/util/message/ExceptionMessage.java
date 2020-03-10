package com.mihai.project.library.util.message;

public class ExceptionMessage {

    /**User**/
    public static String USER_NOT_FIND = "No user was find";
    /** RentRequest **/
    public static String RENT_REQUEST_USER_HAS_RENT_REQUEST = "User already has a rent request for this book";
    public static String RENT_REQUEST_COPY_AVAILABLE = "Copy are available, you can't make a rent request";
    public static String RENT_REQUEST_USER_HAS_A_RENT = "User has a rent for this book";
    public static String RENT_REQUEST_INCORRECT_ID = "No rent request was found";
    /** Book **/
    public static String BOOK_INCORRECT_ID = "No book was found";
    /** Book Rent **/
    public static String BOOK_RENT_FAIL = "Incorrect rent id or current rent was returned";
    public static String BOOK_RENT_NO_BOOK_AVAILABLE = "No book available, please register for one";
    public static String BOOK_RENT_RENT_EXIST = "User already rent this book";

}
