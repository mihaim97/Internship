package com.mihai.project.library.entity.interntable;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.Book;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.entity.interntable.id.BookAuthorId;
import com.mihai.project.library.entity.interntable.id.BookTagId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book_tag_many_to_many")
public class BookTag implements Serializable {

    @EmbeddedId
    private BookTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    private Tag tag;

    public BookTag(){}

    public BookTag(BookTagId id, Book book, Tag tag) {
        this.id = id;
        this.book = book;
        this.tag = tag;
    }

    public BookTagId getId() {
        return id;
    }

    public void setId(BookTagId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookTag)) return false;
        BookTag bookTag = (BookTag) o;
        return Objects.equals(id, bookTag.id) &&
                Objects.equals(book, bookTag.book) &&
                Objects.equals(tag, bookTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, tag);
    }
}
