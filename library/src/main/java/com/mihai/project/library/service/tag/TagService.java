package com.mihai.project.library.service.tag;

import com.mihai.project.library.entity.book.Tag;

import java.util.List;

public interface TagService {
    Tag addTag(Tag tag);

    boolean removeTag(int id);

    Tag updateTag(Tag tag);

    Tag queryTagById(int id);

    Tag queryTagByName(String name);

    List<Tag> queryTags();

    List<Tag> queryTagsLike(String characters);

    <T> T querySingleTagForBookValidation(String name);
}
