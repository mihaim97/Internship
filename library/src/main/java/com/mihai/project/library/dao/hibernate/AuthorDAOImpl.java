package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AuthorDaoHibernateImplementation")
@Qualifier("AuthorDaoHibernate")
public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public Number addAuthor(Author author) {
        return null;
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
