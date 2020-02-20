package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mihai.project.library.entity.book.Book;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class BookTagDTO {

    private int id;
    private String tag;

    @JsonIgnore
    private Book bookId;

    public BookTagDTO() { }

    public BookTagDTO(int id, String tag, Book bookId) {
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
