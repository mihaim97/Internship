package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

//@JsonIgnoreProperties(value = {"id", "dateAdded"}, allowGetters = true)
@JsonPropertyOrder({"title", "description", "authors", "tags" })
public class BookDTO {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private List<AuthorDTO> authors;

    @NotNull
    private List<BookTagDTO> tags;

    public BookDTO(){}

    public BookDTO(@NotNull String title, @NotNull String description, @NotNull List<AuthorDTO> authors, @NotNull List<BookTagDTO> tags) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.tags = tags;
    }

    public BookDTO(int id, @NotNull String title, @NotNull String description, @NotNull Date dateAdded, @NotNull List<AuthorDTO> authors, @NotNull List<BookTagDTO> tags) {
        this.title = title;
        this.description = description;
        this.authors = authors;
        this.tags = tags;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<BookTagDTO> getTags() {
        return tags;
    }

    public void setTags(List<BookTagDTO> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
