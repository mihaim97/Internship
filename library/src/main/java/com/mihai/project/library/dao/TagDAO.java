package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Tag;

import java.util.List;

public interface TagDAO {

    Tag addTag(Tag tag);

    boolean removeTag(Tag tag);

    Tag updateTag(Tag tag);

    Tag queryTagById(int id);

    List<Tag> queryTagByName(String name);

    List<Tag> queryTags();

    List<Tag> queryTagsLike(String characters);

    List<Tag> querySingleTagForBookValidation(String name);
}
