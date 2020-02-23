package com.mihai.project.library.entity.book;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    private List<Author> authors;
    private List<BookTag> tags;

    public Book(){}

    public Book(int id, String title, Date dateAdded) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
    }

    public Book(int id, String title, String description, Date dateAdded, List<Author> authors, List<BookTag> tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.authors = authors;
        this.tags = tags;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<BookTag> getTags() {
        return tags;
    }

    public void setTags(List<BookTag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
