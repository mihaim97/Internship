package com.mihai.project.library.service;

import com.mihai.project.library.contralleradvice.exception.AuthorNotFoundException;
import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyErrorBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDAO authorDAO;

    private MyErrorBuilder errorBuilder;

    public AuthorServiceImpl(@Qualifier("AuthorDaoHibernate") AuthorDAO authorDAO, MyErrorBuilder errorBuilder) {
        this.authorDAO = authorDAO;
        this.errorBuilder = errorBuilder;
    }

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
        Author author = HibernateUtil.getUniqueResult(authorDAO.querySingleAuthorForBookValidation(name));
        if (author == null) {
            throw new AuthorNotFoundException(errorBuilder.getErrorMessageOnAuthorNotFoundException(name));
        }
        return author;
    }

    @Override
    @Transactional
    public Author querySingleAuthorForBookValidation(String name) {
        return HibernateUtil.getUniqueResult(authorDAO.querySingleAuthorForBookValidation(name));

    }
}
