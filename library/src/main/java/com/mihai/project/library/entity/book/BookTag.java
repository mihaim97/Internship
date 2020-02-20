package com.mihai.project.library.entity.book;

public class BookTag {

    private int id;
    private String tag;
    private Book bookId;

    public BookTag() { }

    public BookTag(int id, String tag, Book bookId) {
        this.id = id;
        this.tag = tag;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
