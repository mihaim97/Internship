package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;

import java.util.List;

public interface AuthorDAO {
    public Number addAuthor(Author author);
    public List<Author> queryAuthors();
    public Author querySingleAuthor(String name);
}
