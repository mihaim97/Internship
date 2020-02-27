package com.mihai.project.library.service;

import com.mihai.project.library.entity.book.Author;
import com.mihai.project.library.entity.book.BookTag;

public interface BookTagService {
    BookTag querySingleTahForBookValidation(String name);
}
