package com.mihai.project.library.dao.hibernate;

import com.mihai.project.library.dao.TagDAO;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.MyQuery;
import com.mihai.project.library.util.MyTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tag addTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(tag);
        return tag;
    }

    @Override
    public boolean removeTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(tag);
        return true;
    }

    @Override
    public Tag updateTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(tag);
        return tag;
    }

    @Override
    public Tag queryTagById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Tag.class, id);
    }

    @Override
    public List<Tag> queryTagByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_TAG);
        query.setParameter(MyTable.TAG_FIELD, name);
        return query.getResultList();
    }

    @Override
    public List<Tag> checkIfTagNameExistOnUpdate(String tagName, int currentTagId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_TAG_NAME_ON_UPDATE);
        query.setParameter(MyTable.TAG_FIELD, tagName);
        query.setParameter(MyTable.TAG_ID, currentTagId);
        return query.getResultList();
    }

    @Override
    public List<Tag> queryTags() {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_ALL_TAGS);
        return query.getResultList();
    }

    @Override
    public List<Tag> queryTagsLike(String characters) {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_TAGS_LIKE);
        query.setParameter(MyTable.TAG_CHAR, "%"+characters+"%");
        return query.getResultList();
    }

    @Override
    public List<Tag> querySingleTagForBookValidation(String tagName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Tag> query = session.createQuery(MyQuery.HIBERNATE_QUERY_SINGLE_TAG);
        query.setParameter(MyTable.TAG_FIELD, tagName);
        return query.getResultList();
    }
}
