package com.mihai.project.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonPropertyOrder({"title", "description", "authors", "tags" })
public class BookDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 5, max = 249)
    private String description;

    @Valid
    private Set<AuthorDTO> authors;

    @Valid
    private Set<BookTagDTO> tags;

    public BookDTO(){}

    public BookDTO(@NotBlank @Size(min = 2, max = 50) String title, @NotBlank @Size(min = 5, max = 249) String description, @Valid Set<AuthorDTO> authors, @Valid Set<BookTagDTO> tags) {
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

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    public Set<BookTagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<BookTagDTO> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
