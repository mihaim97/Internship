package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"title", "description", "authors", "tags" })
public class BookDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 5, max = 249)
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
