package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.BookTagDAO;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookTagDAOImpl implements BookTagDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Tag> querySingleTagForBookValidation(String tagName) {
        Query query = entityManager.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_TAG);
        query.setParameter(MyTable.TAG_FIELD, tagName);
        return query.getResultList();
    }
}
