package com.mihai.project.library.dto.book;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mihai.project.library.dto.book.update.TagDTOID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@JsonPropertyOrder({"title", "description", "authors", "tags" })
public class BookDTOQuery {

    @NotNull
    private int id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    @NotNull
    private Set<AuthorDTO> authors;

    @NotNull
    private Set<TagDTOID> tags;

    public BookDTOQuery(){}

    public BookDTOQuery(@NotNull int id, @NotNull String title, @NotNull String description, @NotNull Date dateAdded, @NotNull Set<AuthorDTO> authors, @NotNull Set<TagDTOID> tags) {
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

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDTO> authors) {
        this.authors = authors;
    }

    public Set<TagDTOID> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTOID> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
