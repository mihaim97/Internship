package com.mihai.project.library.service;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO){this.authorDAO = authorDAO;}

    @Override
    public Number addAuthor(Author author) {
      return authorDAO.addAuthor(author);
    }

    @Override
    public List<Author> queryAuthors() {
        return null;
    }

    @Override
    public Author querySingleAuthor(String name) {
        return null;
    }
}
