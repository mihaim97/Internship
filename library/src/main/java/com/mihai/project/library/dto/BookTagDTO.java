package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mihai.project.library.entity.book.Book;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class BookTagDTO {

    private int id;
    private String tag;

    public BookTagDTO() { }

    public BookTagDTO(int id, String tag) {
        this.id = id;
        this.tag = tag;
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
}
