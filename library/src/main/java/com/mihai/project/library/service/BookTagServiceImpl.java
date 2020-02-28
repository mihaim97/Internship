package com.mihai.project.library.service;

import com.mihai.project.library.contralleradvice.exception.NoUniqueResult;
import com.mihai.project.library.dao.BookTagDAO;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.HibernateUtil;
import org.springframework.stereotype.Service;

@Service
public class BookTagServiceImpl implements BookTagService {

    private BookTagDAO bookTagDAO;

    public BookTagServiceImpl(BookTagDAO bookTagDAO) {
        this.bookTagDAO = bookTagDAO;
    }

    @Override
    public Tag querySingleTahForBookValidation(String name) {
        try {
            return HibernateUtil.getUniqueResult(bookTagDAO.querySingleTag(name));
        } catch (NoUniqueResult exc) {
            return null;
        }
    }
}
