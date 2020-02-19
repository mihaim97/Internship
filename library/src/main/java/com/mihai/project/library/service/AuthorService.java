package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Author;

import java.util.List;

public interface AuthorService {
    public Number addAuthor(Author author);
    public List<Author> queryAuthors();
    public Author querySingleAuthor(String name);
}
