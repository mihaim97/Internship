package com.mihai.project.library.service.author;

import com.mihai.project.library.dao.AuthorDAO;
import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.util.HibernateUtil;
import com.mihai.project.library.util.message.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    @Qualifier("AuthorDaoHibernate")
    private AuthorDAO authorDAO;

    @Autowired
    private MessageBuilder errorBuilder;

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
        return author;
    }

    @Override
    @Transactional
    public Author querySingleAuthorForBookValidation(String name) {
        return HibernateUtil.getUniqueResult(authorDAO.querySingleAuthorForBookValidation(name));

    }
}
