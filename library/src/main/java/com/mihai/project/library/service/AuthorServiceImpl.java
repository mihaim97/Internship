package com.mihai.project.library.service;

import com.mihai.project.library.contralleradvice.exception.AuthorNotFoundException;
import com.mihai.project.library.contralleradvice.exception.NoUniqueResult;
import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.MyErrorBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        try {
            Author author = HibernateUtil.getUniqueResult(authorDAO.querySingleAuthor(name));
            if(author == null){
                throw new AuthorNotFoundException(errorBuilder.getErrorMessageOnAuthorNotFoundException(name));
            }
            return author;
        } catch (NoUniqueResult exc) {
            throw new AuthorNotFoundException(errorBuilder.getErrorMessageOnAuthorNotFoundException(name));
        }
    }

    @Override
    public Author querySingleAuthorForBookValidation(String name) {
        try {
            return HibernateUtil.getUniqueResult(authorDAO.querySingleAuthor(name));
        } catch (NoUniqueResult exc) {
           return null;
        }
    }
}
