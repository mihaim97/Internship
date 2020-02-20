package com.mihai.project.library.entity.book;

import com.mihai.project.library.dto.AuthorDTO;

import java.util.Date;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private Date dateAdded;
    private List<Author> authors;
    private List<BookDesc> bookDescriptions;
    private List<BookTag> tags;

    public Book(){}

    public Book(int id, String title, Date dateAdded) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
    }

    public Book(int id, String title, Date dateAdded, List<Author> authors, List<BookDesc> bookDescriptions, List<BookTag> tags) {
        this.id = id;
        this.title = title;
        this.dateAdded = dateAdded;
        this.authors = authors;
        this.bookDescriptions = bookDescriptions;
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

    public List<BookDesc> getBookDescriptions() {
        return bookDescriptions;
    }

    public void setBookDescriptions(List<BookDesc> bookDescriptions) {
        this.bookDescriptions = bookDescriptions;
    }

    public List<BookTag> getTags() {
        return tags;
    }

    public void setTags(List<BookTag> tags) {
        this.tags = tags;
    }
}
