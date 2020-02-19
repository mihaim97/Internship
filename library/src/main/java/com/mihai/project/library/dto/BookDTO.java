package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonIgnoreProperties(value = "id", allowGetters = true)
public class BookDTO {

    private int id;

    @NotNull
    private String title;

    @NotNull
    private Date dateAdded;

    public BookDTO(){}

    public BookDTO(int id, String title, Date dateAdded) {
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
