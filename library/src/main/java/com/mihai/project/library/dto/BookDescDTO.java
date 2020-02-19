package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mihai.project.library.entity.book.Book;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class BookDescDTO {
    private int id;
    private String description;
    private int status;

    @JsonIgnore
    private Book bookId;

    public BookDescDTO() { }

    public BookDescDTO(int id, String description, int status, Book bookId) {
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
