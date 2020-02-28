package com.mihai.project.library.entity.interntable.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookTagId implements Serializable {

    @Column(name = "bookid")
    private Long bookId;

    @Column(name = "tagid")
    private Long tagId;

    public BookTagId(){}

    public BookTagId(Long bookId, Long authId) {
        this.bookId = bookId;
        this.tagId = authId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthId() {
        return tagId;
    }

    public void setAuthId(Long authId) {
        this.tagId = authId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookTagId)) return false;
        BookTagId that = (BookTagId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, tagId);
    }
}
