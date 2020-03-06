package com.mihai.project.library.service.tag;

import com.mihai.project.library.dao.TagDAO;
import com.mihai.project.library.entity.book.Tag;
import com.mihai.project.library.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDAO bookTagDAO;

    @Override
    @Transactional
    public Tag addTag(Tag tag) {
        if (queryTagByName(tag.getName()) != null) {
            return null;
        }
        return bookTagDAO.addTag(tag);
    }

    @Override
    @Transactional
    public boolean removeTag(int id) {
        Tag tag = queryTagById(id);
        if(tag != null){
            return bookTagDAO.removeTag(tag);
        }
        return false;
    }

    @Override
    @Transactional
    public Tag updateTag(Tag tag) {
        Tag existingTag = queryTagById(tag.getId());
        if(existingTag != null){
           if(checkIfTagNameExistOnUpdate(tag.getName(), existingTag.getId())){
               return bookTagDAO.updateTag(tag);
           }
        }
        return null;
    }

    @Override
    @Transactional
    public Tag queryTagById(int id) {
        return bookTagDAO.queryTagById(id);
    }

    @Override
    @Transactional
    public Tag queryTagByName(String name) {
        return HibernateUtil.getUniqueResult(bookTagDAO.queryTagByName(name));
    }

    @Override
    @Transactional
    public boolean checkIfTagNameExistOnUpdate(String tagName, int currentTagId) {
        return bookTagDAO.checkIfTagNameExistOnUpdate(tagName, currentTagId).isEmpty();
    }

    @Override
    @Transactional
    public List<Tag> queryTags() {
        return bookTagDAO.queryTags();
    }

    @Override
    @Transactional
    public List<Tag> queryTagsLike(String characters) {
        return bookTagDAO.queryTagsLike(characters);
    }

    @Override
    @Transactional
    public Tag querySingleTagForBookValidation(String name) {
        return HibernateUtil.getUniqueResult(bookTagDAO.querySingleTagForBookValidation(name));

    }
}
