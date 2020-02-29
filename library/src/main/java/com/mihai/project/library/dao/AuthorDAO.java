package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;

import java.util.List;

public interface AuthorDAO {
    Number addAuthor(Author author);

    List<Author> queryAuthors();

    List<Author> querySingleAuthorForBookValidation(String name);
}
