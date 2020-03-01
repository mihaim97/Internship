package com.mihai.project.library.dto.book.update;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mihai.project.library.dto.book.AuthorDTO;
import com.mihai.project.library.dto.book.TagDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@JsonPropertyOrder({"title", "description", "authors", "tags" })
public class BookDTOID {

    @NotNull
    @Min(1)
    private int id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 5, max = 249)
    private String description;

    @NotNull
    private Date dateAdded;

    @Valid
    private Set<AuthorDTOID> authors;

    @Valid
    private Set<TagDTOID> tags;

    public BookDTOID(){}

    public BookDTOID(@NotNull @Min(1) int id, @NotBlank @Size(min = 2, max = 50) String title, @NotBlank @Size(min = 5, max = 249) String description, @NotNull Date dateAdded, @Valid Set<AuthorDTOID> authors, @Valid Set<TagDTOID> tags) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AuthorDTOID> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTOID> authors) {
        this.authors = authors;
    }

    public Set<TagDTOID> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTOID> tags) {
        this.tags = tags;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
