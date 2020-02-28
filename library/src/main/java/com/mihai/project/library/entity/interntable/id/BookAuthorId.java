package com.mihai.project.library.entity.interntable.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorId implements Serializable {

    @Column(name = "bookid")
    private Long bookId;

    @Column(name = "authorid")
    private Long authId;

    public BookAuthorId(){}

    public BookAuthorId(Long bookId, Long authId) {
        this.bookId = bookId;
        this.authId = authId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAuthorId)) return false;
        BookAuthorId that = (BookAuthorId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(authId, that.authId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authId);
    }
}
