package com.mihai.project.library.entity.book;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book_tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tag")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books;

  /*  @OneToMany(
            mappedBy = "tag",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Book> books;*/


    public Tag() { }

    public Tag(int id, String tag) {
        this.id = id;
        this.name = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(name, tag1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
