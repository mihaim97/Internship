package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Author;

import java.util.List;

public interface AuthorService {
    Number addAuthor(Author author);

    List<Author> queryAuthors();

    Author querySingleAuthor(String name);

    Author querySingleAuthorForBookValidation(String name);
}
