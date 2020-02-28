package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Tag;

import java.util.List;

public interface BookTagDAO {
    List<Tag> querySingleTag(String name);
}
