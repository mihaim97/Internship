package com.mihai.project.library.service;

import com.mihai.project.library.dao.BookTagDAO;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.HibernateUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookTagServiceImpl implements BookTagService {

    private BookTagDAO bookTagDAO;

    public BookTagServiceImpl(BookTagDAO bookTagDAO) {
        this.bookTagDAO = bookTagDAO;
    }

    @Override
    @Transactional
    public Tag querySingleTagForBookValidation(String name) {
        return HibernateUtil.getUniqueResult(bookTagDAO.querySingleTagForBookValidation(name));

    }
}
