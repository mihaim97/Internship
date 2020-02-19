package com.mihai.project.library.entity.book;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private Date dateAdded;

    public Book(){}

    public Book(int id, String title, Date dateAdded) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
