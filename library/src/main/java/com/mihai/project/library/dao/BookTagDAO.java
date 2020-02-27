package com.mihai.project.library.dao;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.BookTag;

import java.util.List;

public interface BookTagDAO {
    List<BookTag> querySingleTag(String name);
}
