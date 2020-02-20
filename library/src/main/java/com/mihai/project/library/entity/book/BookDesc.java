package com.mihai.project.library.entity.book;

public class BookDesc {

    private int id;
    private String description;
    private int status;
    private Book bookId;

    public BookDesc() { }

    public BookDesc(int id, String description, int status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public BookDesc(int id, String description, int status, Book bookId) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
